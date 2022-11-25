package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/tousu.do")
public class TousuServlet extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        String xuehao = request.getParameter("xuehao");
        String wtfl = request.getParameter("wtfl");
        String zywt = request.getParameter("zywt");

        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolts","root","1126");
        Statement stmt=conn.createStatement();
        String sql = "insert ";
        ResultSet rs = stmt.executeQuery(sql);

        

    }
}
