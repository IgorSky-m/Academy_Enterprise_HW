package servlets.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private DataBaseTest dataBase;
    @Override
    public void init() throws ServletException {
        this.dataBase = DataBaseTest.getDataBase();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (!(req.getSession().isNew())) {
           req.getSession().setAttribute("login", dataBase.getAnswerUserName(req.getParameter("login")));
           req.getSession().setAttribute("password", dataBase.getAnswerUserPass(req.getParameter("password")));
           req.getSession().setAttribute("email", dataBase.getAnswerEmail(req.getParameter("email")));

           dataBase.addToDataBase(req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
       }


        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);





    }
}
