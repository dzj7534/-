package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class accountLogin {
    public void Login(/*int user_id,String password*/) throws  Exception {
        //2. 获取连接：如果连接的是本机mysql并且端口是默认的 3306 可以简化书写
        String url = "jdbc:mysql://127.0.0.1:3306/curriculum_design";
        String username = "root";
        String pwd = "root";
        Connection conn = DriverManager.getConnection(url, username, pwd);

        // 定义sql
        String sql = "select * from account where user_id = ? and password = ?";

        // 接收用户输入 用户名和密码
        int user_id = 1;
        String password = "' or '1' = '1";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setInt(1,user_id);
        pstmt.setString(2,password);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();

        // 判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~");
        }

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }
}
