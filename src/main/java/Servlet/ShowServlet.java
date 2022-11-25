package Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.annotation.WebServlet;

@WebServlet("/show")
public class ShowServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolts","root","1126");
            Statement stmt=conn.createStatement();
            String sql = "SELECT message.message_id,sort_id,content,state FROM message inner JOIN feedback on message.message_id = feedback.message_id";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("message_id",rs.getString("message_id"));
                obj.put("sort_id",rs.getString("sort_id"));
                obj.put("content",rs.getString("content"));
                obj.put("state",rs.getString("state"));

                array.add(obj);
            }
            String data = array.toString();
            System.out.println(array);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");//返回的格式必须设置为application/json
            resp.getWriter().write(data);

            rs.close();
            conn.close();
            stmt.close();
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
