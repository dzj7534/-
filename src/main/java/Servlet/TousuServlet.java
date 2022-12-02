package Servlet;

import util.MysqlUtil;

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

        String xuehao = request.getParameter("xuehao");
        String wtfl = request.getParameter("wtfl");
        String zywt = request.getParameter("zywt");

        MysqlUtil util = new MysqlUtil();
        List<Object> list = new ArrayList<>();
        list.add(xuehao);
        list.add(new Date());
        list.add(wtfl);
        list.add(zywt);


        try {
            util.insertData("INSERT INTO message(user_id,submission_time,sort_id,content)VALUES(?,?,?,?)",list);

            List<Object> listSelect = new ArrayList<>();
            listSelect.add(xuehao);

            Object a = util.queryOne("select message_id from message where user_id = ?",listSelect).get("message_id");
            System.out.println(xuehao);
            System.out.println(a);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("提交成功，提交码为"+a);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }
}
