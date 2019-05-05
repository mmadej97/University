package servlets;

import entities.User;
import jdbc.JDBC;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/myprofile")
public class MyProfileServlet extends HttpServlet {

    private User user;
    static {
        JDBC.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        user = JDBC.getUser();
        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/myprofile.html");
        InputStreamReader isrdr = new InputStreamReader(inps);
        BufferedReader buffR = new BufferedReader(isrdr);
        String l;


        while ((l = buffR.readLine()) != null) {

            if (l.contains("[[IMIE]]")) {
                if (user != null) {
                    out.println(user.getImie());
                }
            }
            else if (l.contains("[[NAZWISKO]]")) {
                if (user != null) {
                    out.println(user.getNazwisko());
                }
            }
            else if (l.contains("[[AVATAR]]")) {
                if (user != null) {
                    out.println(user.getUrl());
                }
            }
            else if (l.contains("[[EMAIL]]")) {
                if (user != null) {
                    out.println(user.getEmail());
                }
            }
            else if (l.contains("[[LOGIN]]")) {
                if (user != null) {
                    out.println(user.getLogin());
                }
            }
            else {
                out.print(l);

            }
        }

        String act = request.getParameter("profil");

        if (act == null) {
            //no button has been selected
        } else if (act.equals("PROFIL")) {
            response.sendRedirect("/profile?valid=true");
        } else if (act.equals("WYLOGUJ")) {
            response.sendRedirect("/login");
        }

        act = request.getParameter("edit");

        if (act == null) {
            //no button has been selected
        } else if (act.equals("EDYTUJ")) {
            response.sendRedirect("/edit");
        }

        act = request.getParameter("action");

        if (act == null) {
            //no button has been selected
        } else if (act.equals("DODAJ STRONE")) {
            response.sendRedirect("/add");
        } else if (act.equals("EDYTUJ STRONE")) {
            response.sendRedirect("/update");
        } else if (act.equals("USTAW KOLOR")) {
        response.sendRedirect("/setColor");
        }

    }

}
