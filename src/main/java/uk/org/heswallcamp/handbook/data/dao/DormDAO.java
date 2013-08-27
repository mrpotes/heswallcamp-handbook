package uk.org.heswallcamp.handbook.data.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import uk.org.heswallcamp.handbook.data.model.Dorm;
import uk.org.heswallcamp.handbook.data.model.DormAllocation;
import uk.org.heswallcamp.handbook.data.model.DormAllocation_;
import uk.org.heswallcamp.handbook.data.model.Year;
import uk.org.heswallcamp.handbook.data.model.Year_;

@Singleton
public class DormDAO {

	@Inject Provider<EntityManager> em;

	public void addDorm(Dorm d) {
		em.get().persist(d);
	}

	public List<Dorm> getDorms() {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<Dorm> cq = cb.createQuery(Dorm.class);
		Root<Dorm> dorm = cq.from(Dorm.class);
		cq.select(dorm);
		return em.get().createQuery(cq).getResultList();
	}

	public void delete(Dorm d) {
		em.get().remove(d);
	}
	
	public void storeAllocation(DormAllocation da) {
		em.get().persist(da);
	}
	
	public List<DormAllocation> getAllocations(Integer y) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<DormAllocation> cq = cb.createQuery(DormAllocation.class);
		Root<DormAllocation> mealTime = cq.from(DormAllocation.class);
		Join<DormAllocation, Year> year = mealTime.join(DormAllocation_.year);
		cq.where(cb.equal(year.get(Year_.year), y));
		cq.select(mealTime);
		return em.get().createQuery(cq).getResultList();
	}

}
