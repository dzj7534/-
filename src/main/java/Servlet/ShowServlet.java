package Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.BaseUtil;
import util.MysqlUtil;

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
            BaseUtil util = new BaseUtil();
            Connection conn= util.conn;
            Statement stmt=conn.createStatement();

            String sort_id = LoginServlet.sort_id;


            String sql = "SELECT message.message_id,submission_time,content,state,meaning FROM message inner JOIN feedback on message.message_id = feedback.message_id INNER JOIN sort on sort.sort_id = message.sort_id where message.sort_id = '"+sort_id+"' order by submission_time desc";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("message_id",rs.getString("message_id"));
                obj.put("submission_time",rs.getString("submission_time"));
                obj.put("content",rs.getString("content"));


                String statusId = rs.getString("state");
                String statusStr = "";
                if(statusId.equals("1")){
                    statusStr = "未处理";
                }else if (statusId.equals("2")){
                    statusStr = "处理中";
                }else if (statusId.equals("3")){
                    statusStr = "已完成";
                }

                obj.put("state",statusStr);
                obj.put("meaning",rs.getString("meaning"));


                array.add(obj);
            }
            String data = array.toString();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");//返回的格式必须设置为application/json

            resp.getWriter().write(data);

            conn.close();
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


