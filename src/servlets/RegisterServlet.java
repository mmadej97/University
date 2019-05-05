package servlets;

import entities.User;
import jdbc.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID =1L;
    private static User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/register.html");
        requestDispatcher.include(request, response);


        String act = request.getParameter("rejestruj");
        if (act == null) {
            //no button has been selected
        } else {
            getRegisterData(request,response);
        }


    }



    public static void getRegisterData(HttpServletRequest request, HttpServletResponse response)
    {
        JDBC.init();
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String haslo1 = request.getParameter("haslo");
        String haslo2 = request.getParameter("haslo2");

        if(!haslo1.isEmpty() && haslo1.equals(haslo2) && !login.isEmpty() && !email.isEmpty() ) {
            user = new User(imie, nazwisko, login, email, haslo1);
            JDBC.saveUser(imie, nazwisko, login, email, haslo1);
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
