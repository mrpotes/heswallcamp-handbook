package uk.org.heswallcamp.handbook.data.dao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import uk.org.heswallcamp.handbook.data.model.Year;

@Singleton
public class YearDAO {

    @Inject Provider<EntityManager> em;
    
    public void setYear(Year year) {
    	Year y = em.get().find(Year.class, year.getYear());
    	year.setBoys(y.getBoys());
    	year.setHelpers(y.getHelpers());
    	em.get().persist(year);
    }

	public Year getYear(Integer year) {
		return em.get().find(Year.class, year);
	}

	public void save(Year year) {
		em.get().persist(year);
	}
	
}
