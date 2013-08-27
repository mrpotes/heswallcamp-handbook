package uk.org.heswallcamp.handbook.data;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class HandbookServletContextListener extends GuiceServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("Starting up Guice HandbookServletModule");
		
		String herokuUrl = System.getenv("DATABASE_URL"), username, password, dbUrl, driver, dialect;
		
		if (herokuUrl == null || "".equals(herokuUrl)) {
		    URI dbUri;
			try {
				dbUri = new URI(herokuUrl);
			} catch (URISyntaxException e) {
				throw new IllegalStateException(e);
			}
		    username = dbUri.getUserInfo().split(":")[0];
		    password = dbUri.getUserInfo().split(":")[1];
		    dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		    driver = "org.postgresql.Driver";
		    dialect = "org.hibernate.dialect.PostgreSQLDialect";
		} else {
			username = "SA";
			password = "";
			dbUrl = "jdbc:hsqldb:mem:mymemdb";
			driver = "org.hsqldb.jdbc.JDBCDriver";
			dialect = "org.hibernate.dialect.HSQLDialect";
		}

		System.setProperty("javax.persistence.jdbc.url", dbUrl);
		System.setProperty("javax.persistence.jdbc.user", username);
		System.setProperty("javax.persistence.jdbc.password", password);
		System.setProperty("javax.persistence.jdbc.driver", driver);
		System.setProperty("hibernate.dialect", dialect);
		
		super.contextInitialized(servletContextEvent);
	}
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new HandbookServletModule());	
	}

}
