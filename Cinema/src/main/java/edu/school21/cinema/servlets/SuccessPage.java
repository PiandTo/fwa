package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet(name = "successPage", value = "/successPage")
public class SuccessPage extends HttpServlet {

	@Override
	public void init ( ServletConfig config ) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
		springContext.getBean("userService", UserService.class);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/jsps/successPage.jsp").forward(request, response);
	}
}
