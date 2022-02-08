package edu.school21.cinema.listeners;

import edu.school21.cinema.config.AppConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* This method is called when the servlet context is initialized(when the Web application is deployed). */
		System.out.println("ServletContextListener started");
		sce.getServletContext()
			.setAttribute("springContext", new AnnotationConfigApplicationContext(AppConfig.class));
	}
}
