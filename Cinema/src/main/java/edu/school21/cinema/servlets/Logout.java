package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class Logout extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
		springContext.getBean("userService", UserService.class);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/jsps/welcome.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null && session.getAttribute("user") != null) {
			for(Cookie cookie : cookies){
				if(cookie.getValue().equals(user.getFirstName())){
					loginCookie = cookie;
					session.removeAttribute("user");
					break;
				}
			}
		}
		if(loginCookie != null){
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		request.getRequestDispatcher("/WEB-INF/jsps/welcome.jsp").forward(request, response);
	}
}
