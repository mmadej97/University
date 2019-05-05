package servlets;

import com.sun.deploy.net.HttpRequest;
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


@WebServlet("/setColor")
public class SetColorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private User user;
    private Page page;

    static {
        JDBC.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Page> pages = new ArrayList<Page>();
        user = JDBC.getUser();

        if(request.getParameter("link") == null && request.getParameter("act") == null){
            page = JDBC.getFirstPage();
        }




        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/editpages.html");
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
                if (pages != null) {
                    for (int i = 0; i < pages.size(); i++) {
                        out.println("<form><input type=\"submit\" name=\"link\" value=" + pages.get(i).getTitle()
                                + " /> </form>");
                    }
                }
            }
            else {
                out.print(l);
            }
        }
        String act = null;
        if (pages != null) {
            act = request.getParameter("link");
            if (act == null) {
                // no button has been selected
            } else {
                for (int i = 0; i < pages.size(); i++)
                    if (act.equals(pages.get(i).getTitle())) {
                        JDBC.setSite(pages.get(i));
                    }
            }
        }
        act = request.getParameter("profil");
        if (act == null) {
            // no button has been selected
        } else {
            response.sendRedirect("/myprofile");
        }
        act = request.getParameter("act");
        if (act == null) {
            // no button has been selected
        } else if (act.equals("Przeslij")) {

            String hparam = request.getParameter("Header color");
            String fparam = request.getParameter("Footer color");
            String cparam = request.getParameter("Container color");
            String fontSize = request.getParameter("Font size");
            String fontWeight = request.getParameter("Font weight");
            String fontColor = request.getParameter("Font color");
            JDBC.setColor(hparam, fparam, cparam, fontSize, fontWeight, fontColor);
            response.sendRedirect("/myprofile");
        }
    }


}
