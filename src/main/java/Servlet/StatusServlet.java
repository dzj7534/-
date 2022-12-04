package Servlet;

import util.BaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/sutmitStatus")
public class StatusServlet extends HttpServlet {

    private String message_idStr;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        message_idStr = req.getParameter("message_id");      //取出前端传入的值
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        int stateInt = 0;
        String state = request.getParameter("state");
        String updateStr = "UPDATE feedback set state = ? ,words = ? where message_id = ?";
        String words = request.getParameter("liuyan");
        String message_id = message_idStr;
        BaseUtil util = new BaseUtil();
        List<Object> list = new ArrayList<>();


        switch (state) {
            case "未处理":
                stateInt = 1;
                list.add(stateInt);
                list.add(words);
                break;
            case "办理中":
                stateInt = 2;
                list.add(stateInt);
                list.add(words);
                break;
            case "已完成":
                stateInt = 3;
                list.add(stateInt);
                list.add(words);
                updateStr = "UPDATE feedback set state = ? ,words = ?,finish_time = ? where message_id = ?";
                list.add(new Date());
                break;
        }
        list.add(message_id);

        try {
            util.insertData(updateStr,list);
            response.getWriter().write("提交成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
