package servlets;

import entities.Page;
import entities.User;
import jdbc.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/update")
public class UpdateTextServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private User user;
    static {
        JDBC.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String linkParam = request.getParameter("link");

        user = JDBC.getUser();
        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/color.html");
        InputStreamReader isrdr = new InputStreamReader(inps);
        BufferedReader buffR = new BufferedReader(isrdr);
        String l ;

        List<Page> pages = JDBC.getPages();

        if(linkParam == null && request.getParameter("act1") == null){
            JDBC.getFirstPage();
        }else if(request.getParameter("act1") == null) {

            List<Page> list =pages.stream()
                                  .filter(n -> n.getTitle().equals(linkParam))
                                  .collect(Collectors.toList());

            JDBC.setSite(list.get(0));
        }
        while ((l = buffR.readLine()) != null) {
            if (l.contains("[[LINKS]]")) {
                if (pages != null) {
                    for (int i = 0; i < pages.size(); i++) {
                        out.println("<form ><input type=\"submit\" name=\"link\" value="+pages.get(i).getTitle()+" /> </form>");
                    }
                }

            }else if (l.contains("[[LOGIN]]")) {
                if (user != null) {
                    out.println(user.getLogin());
                }
            } else if (l.contains("[[CONTENT]]")) {
                if(JDBC.getPage()!=null) out.println(JDBC.getPage().getText());
            } else {
                out.print(l);
            }
        }
        String act = request.getParameter("act1");
        if (act == null) {
        } else if (act.equals("Zatwierdz")) {
            String param = request.getParameter("html");
            JDBC.editHtml(param);
            response.sendRedirect("/myprofile");
        }
        act = request.getParameter("profil");
        if (act == null) {
            // no button has been selected
        } else if (act.equals("PROFIL")) {
            response.sendRedirect("/myprofile");
        }
//
//
//        if (pages != null) {
//            act = request.getParameter("link");
//            if (act == null) {
//                // no button has been selected
//            } else {
//                for (int i = 0; i < pages.size(); i++)
//                    if (act.equals(pages.get(i).getTitle())) {
//                        JDBC.setSite(pages.get(i));
//                    }
//
//            }
//        }
    }
 }



