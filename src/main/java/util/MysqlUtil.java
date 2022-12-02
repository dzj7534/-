package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MysqlUtil {

    private static volatile MysqlUtil instance = null;
    public static final String url = "jdbc:mysql://localhost:3306/schoolts";
    public String db = "company?autoReconnect=true";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "1126";
    public Connection connection = null;
    public PreparedStatement pstmt = null;
    public ResultSet resultSet = null;

    public static MysqlUtil getInstance() {
        if (instance == null) {
            synchronized (MysqlUtil.class) {
                if (instance == null) {
                    instance = new MysqlUtil();
                }
            }
        }
        return instance;
    }

    public MysqlUtil() {
        try {
            // 指定连接类型
            Class.forName(driver);

            // 获取连接
            connection = (Connection) DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int queryCount(String sql,List<Object> params) throws SQLException{
        int count = 0;
        int index = 1;
        pstmt = (PreparedStatement) connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        // 返回查询结果
        resultSet= pstmt.executeQuery();
        count  = resultSet.getFetchSize();
        return count;
    }
    public Map<String, Object> queryOne(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pstmt = (PreparedStatement) connection.prepareStatement(sql);
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
        pstmt = (PreparedStatement) connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        pstmt.executeUpdate();
    }

    public List<Map<String, Object>> queryMore(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = (PreparedStatement) connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();

        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }


    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
}
