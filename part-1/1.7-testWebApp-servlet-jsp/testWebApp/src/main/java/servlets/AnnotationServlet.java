package servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AnnotationServletTest",urlPatterns = "/hello")
public class AnnotationServlet extends HttpServlet {
    private Map<String,Integer> sessionMap;
    public AnnotationServlet(){
        sessionMap = new HashMap<>();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        if (name !=null && !(name.trim().isEmpty())) {
            if (sessionMap.containsKey(req.getSession().getId())) {
                Integer counter = sessionMap.get(req.getSession().getId());
                counter++;
                writer.write("<b>Session requests: " + counter +
                        "</b><br/><i><b>Your ID: " +
                        req.getSession().getId() + "</b></i><br/>" +
                        "Yor name: "+name);
                sessionMap.put(req.getSession().getId(),counter);
            } else {
                Integer counter = 1;
                sessionMap.put(req.getSession().getId(),counter);
                writer.write("<b>It's your first request</b> <br/><b>Your ID: "+
                        req.getSession().getId()+
                        "</b></br>Yor name: "+name);
            }
        } else {
            if (sessionMap.containsKey(req.getSession().getId())) {
                Integer counter = sessionMap.get(req.getSession().getId());
                counter++;
                writer.write("<b>Session requests: " + counter + "</b><br/><i><b>Your ID: " + req.getSession().getId() + "</b></i>");
                sessionMap.put(req.getSession().getId(),counter);
            } else {
                Integer counter = 1;
                sessionMap.put(req.getSession().getId(),counter);
                writer.write("<b>It's your first request</b> <br/>Your ID: "+ req.getSession().getId());
            }


        }









    }

}