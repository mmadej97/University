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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private User user;
    private Page page;

    static {
        JDBC.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = JDBC.getUser();

        if(request.getParameter("valid") != null && request.getParameter("valid").equals("true")) {
            page = JDBC.getFirstPage();
        }else{
            page = JDBC.getPage();
        }


        PrintWriter out = response.getWriter();
        ServletContext headerContext = this.getServletContext();
        InputStream inps = headerContext.getResourceAsStream("/html/profile.html");
        InputStreamReader isrdr = new InputStreamReader(inps);
        BufferedReader buffR = new BufferedReader(isrdr);
        String l;


        while ((l = buffR.readLine()) != null) {

            if (l.contains("[[LOGIN]]")) {
                if (user != null) {
                    out.println(user.getLogin());
                }
            }
            else if (l.contains("[[HCOLOR]]")) {
                if(page!=null) out.println("background: " +page.getHeaderColor()+";");
            }
            else if (l.contains("[[FCOLOR]]")) {
                if(page!=null)	out.println("background: " +page.getFooterColor()+";");
            }
            else if (l.contains("[[CONTAINERCOLOR]]")) {
                if(page!=null)	out.println("background: " +page.getContainerColor()+";");
            }
            else if (l.contains("[[FONTSIZE]]")) {
                if(page!=null)	out.println("font-size: " +page.getFontSize()+";");
            }
            else if (l.contains("[[FONTWEIGHT]]")) {
                if(page!=null)	out.println("font-weight: " +page.getFontWeight()+";");
            }
            else if (l.contains("[[FONTCOLOR]]")) {
                if(page!=null)	out.println("color: " +page.getFontColor()+";");
            }
            else if (l.contains("[[CONTENT]]")) {
                if (page != null) {
                    out.println(page.getText());
                }
                else {
                    out.println("Brak strony");
                }
            }

            else {
                out.print(l);
            }
        }

        String act = request.getParameter("profile");
        if (act == null) {
            //no button has been selected
        } else{
            response.sendRedirect("/myprofile");
        }
    }
}
