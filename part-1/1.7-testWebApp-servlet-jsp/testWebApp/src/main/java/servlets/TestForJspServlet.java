package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jspTestServlet", urlPatterns = "/jsp")
public class TestForJspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.getSession().setAttribute("NAME",name);
        String bigData = "BIG DATA";
        req.getSession().setAttribute("name",name);
        req.getSession().setAttribute("key",bigData);
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/testservlet.jsp").forward(req,resp);



    }
}
