package edu.school21.cinema.filters;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LogAuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile", "/signIn", "/signUp"})
public class MainFilter implements Filter {
    LogAuthService logAuthService;

    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.logAuthService = springContext.getBean("logAuthService", LogAuthService.class);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean isLogging = (session != null);

        if (isLogging && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Utils utils = new Utils(logAuthService);
            utils.setLogs(request, user);
            utils.setRequest(request, user, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setStatus(403);
            if (((HttpServletRequest) request).getRequestURI().equals("/signUp")){
                request.getRequestDispatcher("/WEB-INF/jsps/signUp.jsp").forward(request, resp);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsps/signIn.jsp").forward(request, resp);
            }
        }
    }
}
