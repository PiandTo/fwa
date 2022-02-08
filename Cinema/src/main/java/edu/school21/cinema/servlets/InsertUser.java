package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

@WebServlet(name = "signup_result", value = "/signup_result")
public class InsertUser extends HttpServlet {
	private UserService usersService;

	@Override
	public void init ( ServletConfig config ) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
		this.usersService = springContext.getBean("userService", UserService.class);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone_number");
		String password = request.getParameter("password");
		User user = new User(null, firstName, lastName, email, phoneNumber, password);
		try {
			if (!usersService.findByEmail(user.getEmail())) {
				usersService.save(user);
				request.getRequestDispatcher("successPage").forward(request, response);
//				response.sendRedirect("http://localhost:8080/successPage");
			} else {
				request.getRequestDispatcher("failPage").forward(request, response);
//				response.sendRedirect("http://localhost:8080/failPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}
}