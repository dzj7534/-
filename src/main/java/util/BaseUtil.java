package util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class BaseUtil {
    private String DRIVER;
    private String URL;
    private String USER;
    private String PASS;

    public Connection conn;
    public PreparedStatement stm;
    public ResultSet rs;
    public PreparedStatement pstmt = null;
    public ResultSet resultSet = null;


    public BaseUtil() {
        //初始化配置文件
        this.init("database.cfg.xml");
    }

    public void init(String filename) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);

            Document doc = new SAXReader().read(is);

            Element root = doc.getRootElement();
            Iterator<Element> actionsIt = root.elements("databases").iterator();
            Element actions = actionsIt.next();
            Iterator<Element> actionIt = actions.elements("database").iterator();
            Element action = actionIt.next();
            for (Iterator<Element> resultIt = action.elementIterator("property"); resultIt.hasNext();) {
                Element resultElement = resultIt.next();
                String name = resultElement.attributeValue("name");
                if (name.equals("driver")) {
                    DRIVER = resultElement.getText();
                } else if (name.equals("url")) {
                    URL = resultElement.getText();
                } else if (name.equals("user")) {
                    USER = resultElement.getText();
                } else if (name.equals("pass")) {
                    PASS = resultElement.getText();
                }
            }

            try {
                // 指定连接类型
                Class.forName(DRIVER);

                // 获取连接
                conn = (Connection) DriverManager.getConnection(URL, USER, PASS);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public <T> List<T> getList(Class<T> cls, String sql, Object... params) {
        //
        List<T> list = null;
        Connection conn1 = null;
        PreparedStatement stm1 = null;
        ResultSet rs1 = null;

        try {
            Class.forName(DRIVER);  //加载驱动类
            conn1 = DriverManager.getConnection(URL, USER, PASS);//创建连接
            //conn1 = this.open();

            stm1 = conn1.prepareStatement(sql); //创建一个执行命令的类
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    stm1.setObject(i + 1, params[i]);// 为SQL语句添加SQL参数
                }
            }
            rs1 = stm1.executeQuery();//执行命令返回结果
            //利用JAVA反射技术，将结果集ResultSet，映射到集合List<T>
            if (rs1 != null) {
                list = new ArrayList<T>();
                List<Method> methods = matchPojoMethods(cls.getDeclaredMethods(), "set");

                while (rs1.next()) {
                    T t = cls.newInstance();
                    for (Method m : methods) {
                        String cName = m.getName().substring(3).toLowerCase();

                        Object param = null;
                        if (m.getParameterTypes()[0] == String.class) {
                            param = rs1.getString(cName);
                        } else if (m.getParameterTypes()[0] == int.class) {
                            param = rs1.getInt(cName);
                        } else if (m.getParameterTypes()[0] == boolean.class) {
                            param = rs1.getBoolean(cName);
                        } else if (m.getParameterTypes()[0] == float.class) {
                            param = rs1.getFloat(cName);
                        }
                        m.invoke(t, param);
                    }
                    list.add(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(conn1, stm1, rs1);
        }

        return list;
    }

    //java反射技术
    private List<Method> matchPojoMethods(Method[] methods, String methodName) {

        // List容器存放所有带get字符串的Method对象
        List<Method> list = new ArrayList<Method>();

        // 过滤当前Pojo类所有带get字符串的Method对象,存入List容器
        for (int index = 0; index < methods.length; index++) {
            if (methods[index].getName().indexOf(methodName) != -1) {
                list.add(methods[index]);
            }
        }
        return list;
    }



    private void close(Connection conn, Statement stm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stm != null) {
                stm.close();
                stm = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Map<String, Object> queryOne(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();// 返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < col_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
        }
        return map;
    }

    public void insertData(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        pstmt.executeUpdate();
    }



    protected void finalize() throws Throwable {
        super.finalize();
        conn.close();
    }


}
