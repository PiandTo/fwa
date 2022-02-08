package edu.school21.cinema.filters;

import edu.school21.cinema.models.Log;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LogAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Utils {
    private final LogAuthService logAuthService;

    public Utils(LogAuthService logAuthService) {
        this.logAuthService = logAuthService;
    }

    public void setRequest(ServletRequest request, User user, ServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", user.getFirstName());
        request.setAttribute("surname", user.getLastName());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("id", user.getId());
        request.setAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
        request.getRequestDispatcher("/WEB-INF/jsps/profile.jsp").forward(request, response);
    }

    public void setLogs(ServletRequest request, User user) {
        Log log = new Log();
        log.setEmail(user.getEmail());
        log.setIp(request.getRemoteAddr());
        try {
            logAuthService.save(log);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
