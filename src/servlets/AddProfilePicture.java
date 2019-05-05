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

@WebServlet("/edit")
public class AddProfilePicture extends HttpServlet {

    private User user;
    static {
        JDBC.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        user = JDBC.getUser();
        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/edit.html");
        InputStreamReader isrdr = new InputStreamReader(inps);
        BufferedReader buffR = new BufferedReader(isrdr);
        String l = null;
        while ((l = buffR.readLine()) != null) {
            if (l.contains("[[LOGIN]]")) {
                if (user != null) {
                    out.println(user.getLogin());
                }
            } else {
                out.print(l);
            }
        }

        String act = request.getParameter("profil");
        if (act == null) {
            // no button has been selected
        } else if (act.equals("PROFIL")) {
            response.sendRedirect("/myprofile");
        }
        act = request.getParameter("act");
        if (act == null) {
        } else if (act.equals("Przeslij")) {
            String param = request.getParameter("url");
            JDBC.updateUrl(param);
            response.sendRedirect("/myprofile");
        }

    }
}
