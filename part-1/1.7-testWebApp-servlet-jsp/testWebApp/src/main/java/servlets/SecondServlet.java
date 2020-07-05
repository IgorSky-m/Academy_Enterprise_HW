package servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@WebServlet(name = "Second",urlPatterns = "/second")
public class SecondServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("image/png");
            OutputStream os = resp.getOutputStream();
        try {
            URL imageURL = SecondServlet.class.getResource("1.png");
            BufferedImage img = ImageIO.read(imageURL);
            ImageIO.setUseCache(false);
            ImageIO.write(img,"png",os);
        } finally {
            os.close();

        }

    }
}
