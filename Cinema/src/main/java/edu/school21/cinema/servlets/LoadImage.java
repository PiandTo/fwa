package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import java.io.*;
import java.util.Properties;

@WebServlet("/loadImage/*")
public class LoadImage extends HttpServlet {

    @Override
    public void init ( ServletConfig config ) throws ServletException {
        super.init(config);
        System.out.println("ServletContextListener started");
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        springContext.getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filename = req.getRequestURI().substring(req.getRequestURI().indexOf("/", 1) + 1);
        resp.setContentType("image/jpeg");
        ServletOutputStream out;
        out = resp.getOutputStream();
        FileInputStream fis = new FileInputStream("src/main/webapp/WEB-INF/application.properties");
        Properties property = new Properties();
        property.load(fis);
        FileInputStream fin = new FileInputStream(property.getProperty("images.upload.path") + filename);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch;
        while((ch=bin.read())!=-1)
        {
            bout.write(ch);
        }
        bin.close();
        fin.close();
        bout.close();
        out.close();
    }
}
