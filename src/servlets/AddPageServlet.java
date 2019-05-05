package servlets;

import entities.Page;
import entities.User;
import jdbc.JDBC;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add")
public class AddPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private User user;
    static {
        JDBC.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Page> pages ;
        user = JDBC.getUser();
        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/add.html");
        InputStreamReader isrdr = new InputStreamReader(inps);
        BufferedReader buffR = new BufferedReader(isrdr);
        String l = null;
        pages = JDBC.getPages();
        while ((l = buffR.readLine()) != null) {
            if (l.contains("[[LOGIN]]")) {
                if (user != null) {
                    out.println(user.getLogin());
                }

            } else if (l.contains("[[LINKS]]")) {
                if (user != null) {
                    for (int i = 0; i < pages.size(); i++) {
                        out.println("<form><input type=\"submit\" name=\"link\" value=" + pages.get(i).getTitle()
                                + " />" + "	<input type=\"submit\" name=\"delete\" value=usun_"
                                + pages.get(i).getTitle() + "> </br>" + " </form>");
                    }
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
            String param = request.getParameter("html");
            String title = request.getParameter("title");
            JDBC.updateHtml(param, title);
            response.sendRedirect("/myprofile");
        }
        if (pages != null) {
            act = request.getParameter("link");
            if (act == null) {
                // no button has been selected
            } else {
                for (int i = 0; i < pages.size(); i++)
                    if (act.equals(pages.get(i).getTitle())) {
                        JDBC.setSite(pages.get(i));
                        response.sendRedirect("/profile");
                    }
            }
            act = request.getParameter("delete");
            if (act == null) {
                // no button has been selected
            } else {
                for (int i = 0; i < pages.size(); i++)
                    if (act.equals("usun_" + pages.get(i).getTitle())) {
                        JDBC.deleteSite(pages.get(i));
                        response.sendRedirect("/myprofile");
                    }
            }
        }
    }
}
