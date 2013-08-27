package uk.org.heswallcamp.handbook.data.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uk.org.heswallcamp.handbook.data.model.Boy;
import uk.org.heswallcamp.handbook.data.model.Boy_;
import uk.org.heswallcamp.handbook.data.model.Year;
import uk.org.heswallcamp.handbook.data.model.Year_;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Singleton
public class BoysDAO {

	@Inject Provider<EntityManager> em;
	@Inject YearDAO yearDAO;

	public Boy getBoy(String name) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<Boy> cq = cb.createQuery(Boy.class);
		Root<Boy> boy = cq.from(Boy.class);
		cq.where(cb.equal(boy.get(Boy_.name), name));
		cq.select(boy);
		return em.get().createQuery(cq).getSingleResult();
	}

	public List<Boy> getBoys(Integer y) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<Boy> cq = cb.createQuery(Boy.class);

		Root<Boy> boy = cq.from(Boy.class);
		Root<Year> year = cq.from(Year.class);
		cq.where(cb.equal(year.get(Year_.year), y));
		cq.where(year.in(boy.get(Boy_.camps)));
		cq.select(boy);
		return em.get().createQuery(cq).getResultList();
	}

	public void save(Boy boy) {
		boy.setCamps(Lists.transform(boy.getCamps(), YEAR_TO_YEAR_ENTITY));
		em.get().persist(boy);
//		for (Year year : boy.getCamps()) {
//			Year y;
//			try {
//				y = em.get().find(Year.class, year.getYear());
//			} catch (NoResultException e) {
//				em.get().persist(entity)
//			}
//			em.get().persist(y);
//		}
	}

	private final Function<Year, Year> YEAR_TO_YEAR_ENTITY = new Function<Year, Year>() {
		public Year apply(Year year) {
			try {
				return em.get().find(Year.class, year.getYear());
			} catch (NoResultException e) {
				em.get().persist(year);
				return year;
			}
		}
	};

}
