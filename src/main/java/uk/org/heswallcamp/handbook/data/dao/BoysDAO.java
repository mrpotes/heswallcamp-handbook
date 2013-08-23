package uk.org.heswallcamp.handbook.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import uk.org.heswallcamp.handbook.data.model.Boy;
import uk.org.heswallcamp.handbook.data.model.Year;

import com.google.appengine.api.datastore.Key;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Singleton
public class BoysDAO {

	@Inject Provider<EntityManager> em;
	@Inject YearDAO yearDAO;

	public Boy getBoy(String name) {
		Boy boy = (Boy) em.get().createQuery("SELECT b FROM Boy b WHERE name = :name")
				.setParameter("name", name)
				.getSingleResult();
		boy.setCamps(Lists.transform(boy.getCampYears(), KEY_TO_YEAR));
		return boy;
	}

	public List<Boy> getBoys(Integer year) {
		List<Key> boyKeys = new ArrayList<Key>(yearDAO.getYear(year).getBoys());
		List<Boy> results = Lists.transform(boyKeys, NAME_TO_BOY);
		for (Boy boy : results) {
			boy.setCamps(Lists.transform(boy.getCampYears(), KEY_TO_YEAR));
		}
		return results;
	}

	public void save(Boy boy) {
		boy.setCampYears(Lists.transform(boy.getCamps(), YEAR_TO_KEY));
		em.get().persist(boy);
		for (Key year : boy.getCampYears()) {
			Year y = em.get().find(Year.class, year);
			y.getBoys().add(boy.getKey());
			em.get().persist(y);
		}
	}

	private final Function<Integer, Key> YEAR_TO_KEY = new Function<Integer, Key>() {
		public Key apply(Integer y) {
			return yearDAO.getYear(y).getKey();
		}
	};

	private final Function<Key, Integer> KEY_TO_YEAR = new Function<Key, Integer>() {
		public Integer apply(Key y) {
			return em.get().find(Year.class, y).getYear();
		}
	};

	private final Function<Key, Boy> NAME_TO_BOY = new Function<Key, Boy>() {
		public Boy apply(Key name) {
			Boy boy = em.get().find(Boy.class, name);
			boy.setCamps(Lists.transform(boy.getCampYears(), KEY_TO_YEAR));
			return boy;
		}
	};

}
