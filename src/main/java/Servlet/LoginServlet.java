package Servlet;

import util.BaseUtil;
import util.User;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        BaseUtil util = new BaseUtil();
        String sql = "select * from cd_user where username=? and password=?";
        List<User> list = util.getList(User.class, sql, username, password);

        if (list != null && list.size() > 0) {
            User user = list.get(0);
            if(user != null && username.equals(user.getUserName())){
                HttpSession session=request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/schoolTs_war_exploded/shouye.html");

            }else{
                response.sendRedirect("/schoolTs_war_exploded/login.html?error=yes");
            }

        }else{
            System.out.println("list 为空");
        }





    }
}
