package uk.org.heswallcamp.handbook.data.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uk.org.heswallcamp.handbook.data.model.NightShift;
import uk.org.heswallcamp.handbook.data.model.NightShiftAllocation;
import uk.org.heswallcamp.handbook.data.model.NightShiftAllocation_;
import uk.org.heswallcamp.handbook.data.model.NightShift_;
import uk.org.heswallcamp.handbook.data.model.Year;
import uk.org.heswallcamp.handbook.data.model.Year_;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

@Singleton
public class NightShiftDAO {

	@Inject Provider<EntityManager> em;

	private Function<NightShift, List<NightShiftAllocation>> shiftAllocations = new Function<NightShift, List<NightShiftAllocation>>() {
		public List<NightShiftAllocation> apply(NightShift shift) {
			CriteriaBuilder cb = em.get().getCriteriaBuilder();
			CriteriaQuery<NightShiftAllocation> cq = cb.createQuery(NightShiftAllocation.class);

			Root<NightShiftAllocation> shiftAllocation= cq.from(NightShiftAllocation.class);
			cq.where(cb.equal(shiftAllocation.get(NightShiftAllocation_.nightShift), shift));
			cq.select(shiftAllocation);
			return em.get().createQuery(cq).getResultList();
		}
	};

	public Map<NightShift, List<NightShiftAllocation>> getShifts(Integer year) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<NightShift> cq = cb.createQuery(NightShift.class);

		Root<NightShift> shift = cq.from(NightShift.class);
		Root<Year> yearEntity = cq.from(Year.class);
		cq.where(cb.equal(yearEntity.get(Year_.year), year));
		cq.where(yearEntity.in(shift.get(NightShift_.year)));
		cq.select(shift);
		return Maps.toMap(em.get().createQuery(cq).getResultList(), shiftAllocations);
	}

	public void save(NightShiftAllocation allocation) {
		em.get().persist(allocation);
	}

}
