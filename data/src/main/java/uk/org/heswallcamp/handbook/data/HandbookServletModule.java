package uk.org.heswallcamp.handbook.data;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class HandbookServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		install(new JpaPersistModule("handbook-persistence"));
		filter("/data/*").through(PersistFilter.class);
		serve("/data/boys*").with(BoysServlet.class);
	}
	
}
