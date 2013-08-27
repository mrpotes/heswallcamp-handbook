package uk.org.heswallcamp.handbook.data.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import uk.org.heswallcamp.handbook.data.model.Boy;
import uk.org.heswallcamp.handbook.data.model.Year;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Singleton
public class HelperDAO {

	@Inject Provider<EntityManager> em;
	@Inject YearDAO yearDAO;

	public Boy getBoy(String name) {
		Boy boy = (Boy) em.get().createQuery("SELECT b FROM Boy b WHERE name = :name")
				.setParameter("name", name)
				.getSingleResult();
		return boy;
	}

	public List<Boy> getBoys(Integer year) {
		List<Boy> results = (List<Boy>) em.get().createQuery("SELECT b FROM Boy b, Year y WHERE y in b.camps AND y.year = :year")
				.setParameter("year", year)
				.getResultList();
		return results;
	}

	public void save(Boy boy) {
		boy.setCamps(Lists.transform(boy.getCamps(), YEAR_TO_YEAR_ENTITY));
		em.get().persist(boy);
		for (Year year : boy.getCamps()) {
			Year y = em.get().find(Year.class, year.getYear());
			em.get().persist(y);
		}
	}

	private final Function<Year, Year> YEAR_TO_YEAR_ENTITY = new Function<Year, Year>() {
		public Year apply(Year year) {
			return em.get().find(Year.class, year.getYear());
		}
	};

}
