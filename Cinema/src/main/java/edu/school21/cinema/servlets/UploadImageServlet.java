package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import edu.school21.cinema.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@WebServlet(name = "/UploadImageServlet", value = "/images")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10Mb
public class UploadImageServlet extends HttpServlet {

    @Override
    public void init ( ServletConfig config ) throws ServletException {
        super.init(config);
        System.out.println("ServletContextListener started");
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        springContext.getBean("userService", UserService.class);
    }

    public static String UploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fis = new FileInputStream("src/main/webapp/WEB-INF/application.properties");
        Properties property = new Properties();
        property.load(fis);
        User user = (User) request.getSession().getAttribute("user");
        String savePath = property.getProperty("images.upload.path") + user.getId() + "/";

        File fileSaveDir = new File(savePath);
        System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
        Path path = Paths.get(savePath);
        if (!Files.exists(path)) {
            fileSaveDir.mkdirs();
        }
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            System.out.println("FileName: " + fileName);
            File f = new File(savePath + fileName);
            if (!f.exists())
                part.write(savePath + fileName);
            else return (null);
        }
        return savePath +  fileName;
    }

        public static String extractFileName (Part part){
            String contentDisp = part.getHeader("content-disposition"); // https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition
            String[] items = contentDisp.split(";");
            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    return s.substring(s.indexOf("=") + 2, s.length() - 1);
                }
            }
            return "";
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String fileName = UploadImage(request, response);
            if (fileName == null) {
                request.setAttribute("message", "File already exists!");
                getServletContext().getRequestDispatcher("/WEB-INF/jsps/images.jsp").forward(
                        request, response);
            }
            else {
                System.out.println(fileName);

                request.setAttribute("message", fileName + "<br> File uploaded successfully!");
                getServletContext().getRequestDispatcher("/WEB-INF/jsps/images.jsp").forward(
                        request, response);
            }
        }
}
