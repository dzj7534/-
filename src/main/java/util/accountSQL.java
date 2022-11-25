package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class accountSQL {
    /**
     * 查询所有
     * 1. SQL：select * from account;
     * 2. 参数：不需要
     * 3. 结果：List<account>
     */

    public void selectAll() throws Exception {
        // 获取Connection
        // 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //定义SQL
        String sql = "select * from account;";

        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数

        //执行SQL
        ResultSet rs = pstmt.executeQuery();

        //处理结果 List<account> 封装account对象，装载List集合
        account acc = null;
        List<account> accs = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            int id = rs.getInt("id");
            int user_id = rs.getInt("user_id");
            String password = rs.getString("password");
            String sort_id = rs.getString("sort_id");
            //封装account对象
            acc = new account(id, user_id, password, sort_id);
            //装载集合
            accs.add(acc);
        }
        System.out.println(accs);
        //释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }


    /**
     * 添加
     * 1. SQL：insert into account(user_id,password,sort_id) values(?,?,?);
     * 2. 参数：需要，除了id之外的所有参数信息
     * 3. 结果：boolean
     */

    public void Add(/*Integer user_id,String password,String sort_id*/) throws Exception {
        //Test
        Integer user_id=1;
        String password="ASDAW";
        String sort_id="0";
        //获取Connection
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //定义SQL
        String sql = "insert into account(user_id,password,sort_id) values(?,?,?);";

        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数
        pstmt.setInt(1, user_id);
        pstmt.setString(2, password);
        pstmt.setString(3, sort_id);
        //执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //处理结果
        System.out.println(count > 0);

        //释放资源
        pstmt.close();
        conn.close();
    }


    /**
     * 根据表id 修改
     * 1. SQL：
     * update account
     * set user_id  = ?,
     * password = ?,
     * sort_id= ?,
     * 2. 参数：需要，所有数据
     * 3. 结果：boolean
     */

    public void Update(/*Integer id, Integer user_id, String password, String sort_id*/) throws Exception {
        //Test
        int id = 2;
        Integer user_id=1;
        String password="ASDAW";
        String sort_id="0";

        //获取Connection
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //定义SQL
        String sql = " update account\n" +
                "         set user_id  = ?,\n" +
                "         password= ?,\n" +
                "         sort_id     = ?\n" +
                "     where id = ?";

        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数
        pstmt.setInt(1, user_id);
        pstmt.setString(2, password);
        pstmt.setString(3, sort_id);
        pstmt.setInt(4, id);

        //执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //处理结果
        System.out.println(count > 0);
        //释放资源
        pstmt.close();
        conn.close();
    }


    /**
     * 根据表id 删除
     * 1. SQL：
     * delete from acc where id = ?
     * 2. 参数：需要，id
     * 3. 结果：boolean
     */

    public void DeleteById(/*int id*/) throws Exception {
        // test
        int id = 1;

        //获取Connection
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //定义SQL
        String sql = " delete from account where id = ?";

        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置参数
        pstmt.setInt(1, id);

        //执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //处理结果
        System.out.println(count > 0);

        //释放资源
        pstmt.close();
        conn.close();

    }


}
