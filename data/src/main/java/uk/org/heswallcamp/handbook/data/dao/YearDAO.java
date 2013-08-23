package uk.org.heswallcamp.handbook.data.dao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import uk.org.heswallcamp.handbook.data.model.Year;

@Singleton
public class YearDAO {

    @Inject Provider<EntityManager> em; 

	public Year getYear(Integer year) {
		Query q = em.get().createQuery("select from Year where year = :year");
		q.setParameter("year", year);
		Year y = (Year) q.getSingleResult();
		if (y == null) {
			y = new Year();
			y.setYear(year);
			em.get().persist(y);
		}
		return y;
	}
	
}
