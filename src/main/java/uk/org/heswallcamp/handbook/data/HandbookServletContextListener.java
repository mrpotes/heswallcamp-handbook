package uk.org.heswallcamp.handbook.data;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class HandbookServletContextListener extends GuiceServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("Starting up Guice HandbookServletModule");
		super.contextInitialized(servletContextEvent);
	}
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new HandbookServletModule());	
	}

}
