package Servlet;

import util.BaseUtil;
import util.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/pingfen")
public class PingfenServlet extends HttpServlet {
    private String message_idStr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        message_idStr = req.getParameter("message_id");      //取出前端传入的值
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseUtil util = new BaseUtil();
        String pingfen = req.getParameter("pingfen");
        List<Object> list = new ArrayList<>();
        list.add(pingfen);
        list.add(message_idStr);
        try {
            util.insertData("update feedback set satisfaction = ? where message_id = ?",list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("提交成功");
    }
}
