package edu.school21.cinema.servlets;

import edu.school21.cinema.filters.Utils;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LogAuthService;
import edu.school21.cinema.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;

@WebServlet(name = "profilePage", value = "/profilePage")
public class CheckUser extends HttpServlet {
    private UserService usersService;
    private LogAuthService logAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean("userService", UserService.class);
        this.logAuthService = springContext.getBean("logAuthService", LogAuthService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Utils utils = new Utils(logAuthService);
            utils.setLogs(request, user);
            utils.setRequest(request, user, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsps/signIn.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null || sessionUser.getEmail().equals(request.getParameter("email"))) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            try {
                if (usersService.findByEmail(email)) {
                    User user = usersService.findObjByEmail(email);
                    if (usersService.equalsScript(password, email)) {
                        session.setAttribute("user", user);
                        Cookie userName = new Cookie("user", user.getFirstName());
                        response.addCookie(userName);
                        Utils utils = new Utils(logAuthService);
                        utils.setLogs(request, user);
                        utils.setRequest(request, user, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "signIn");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "signIn");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/jsps/signIn.jsp").forward(request, response);
        }
    }
    public void destroy() {
    }
}
