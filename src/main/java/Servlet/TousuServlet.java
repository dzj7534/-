package Servlet;

import util.BaseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/tousu.do")
public class TousuServlet extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int xuehao = LoginServlet.user_id;
        String wtfl = request.getParameter("wtfl");
        String zywt = request.getParameter("zywt");
        BaseUtil util = new BaseUtil();
        List<Object> list = new ArrayList<>();
        list.add(xuehao);
        list.add(new Date());
        list.add(wtfl);
        list.add(zywt);

        try {
            util.insertData("INSERT INTO message(user_id,submission_time,sort_id,content)VALUES(?,?,?,?)",list);

            List<Object> listSelect = new ArrayList<>();
            listSelect.add(xuehao);

            Object message_id = util.queryOne("select message_id from message where user_id = ?",listSelect).get("message_id");

            List<Object> listFeedback = new ArrayList<>();
            listFeedback.add(message_id);
            listFeedback.add(1);
            util.insertData("INSERT INTO feedback(message_id,state)VALUES(?,?)",listFeedback);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("提交成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }
}
