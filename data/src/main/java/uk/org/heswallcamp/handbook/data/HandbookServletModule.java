package uk.org.heswallcamp.handbook.data;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class HandbookServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		install(new JpaPersistModule("handbook-persistence"));
		bind(GuiceContainer.class);
		ResourceConfig rc = new PackagesResourceConfig("uk.org.heswallcamp.handbook.data.resources");
		for (Class<?> resource : rc.getClasses()) {
			bind(resource).in(Scopes.SINGLETON);
		}
		bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
		filter("/data/*").through(PersistFilter.class);
		serve("/data/*").with(GuiceContainer.class);
	}

}
