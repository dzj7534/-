package Servlet;

import util.BaseUtil;
import util.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

    static String sort_id;
    static int user_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        BaseUtil util = new BaseUtil();
        String sql = "select * from account where user_id=? and password=?";
        List<account> list = util.getList(account.class, sql, username, password);

        if (list != null && list.size() > 0) {
            account user = list.get(0);
            if(user != null && Integer.parseInt(username) == user.getUser_id()){
                HttpSession session=request.getSession();
                session.setAttribute("user", user);
                sort_id = user.getSort_id();
                user_id = user.getUser_id();
                if(sort_id.equals("01")){

                    response.sendRedirect("/schoolTs_war_exploded/shouye.html");
                }else{
                    response.sendRedirect("/schoolTs_war_exploded/show.html");
                }
            }

        }else{
            response.sendRedirect("/schoolTs_war_exploded/login.html");
        }





    }
}
