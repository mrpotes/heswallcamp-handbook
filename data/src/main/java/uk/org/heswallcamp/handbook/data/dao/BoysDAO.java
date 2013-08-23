package uk.org.heswallcamp.handbook.data.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import uk.org.heswallcamp.handbook.data.model.Boy;
import uk.org.heswallcamp.handbook.data.model.Year;

import com.google.appengine.api.datastore.Key;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Singleton
public class BoysDAO {

	@Inject Provider<EntityManager> em;

	public Boy getBoy(Key id) {
		return em.get().find(Boy.class, id);
	}

	public List<Boy> getBoys(Integer year) {
		Query q = em.get().createQuery("select b from Boy b, Year y where b.camps contains y and y.year = :year");
		q.setParameter("year", year);
		return (List<Boy>) q.getResultList();
	}

	public void save(Boy boy) {
		boy.setCamps(Lists.transform(boy.getCamps(), new YearsMapper()));
		em.get().persist(boy);
	}

	private static final class YearsMapper implements Function<Year, Year> {
		public Year apply(Year y) {
			return new YearDAO().getYear(y.getYear());
		}
	}

}
