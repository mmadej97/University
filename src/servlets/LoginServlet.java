package servlets;

import jdbc.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static{
        JDBC.init();
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/login.html");
        requestDispatcher.include(request,response);


        String act = request.getParameter("act");
        if (act == null) {
            //no button has been selected
        } else if (act.equals("Zaloguj")) {
            String login = request.getParameter("login");
            String password = request.getParameter("haslo");
            if(JDBC.checkUser(login, password))
                response.sendRedirect("/myprofile");
        } else if (act.equals("Nowe konto")) {
            response.sendRedirect("/register");
        }
    }

}
