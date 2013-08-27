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

import org.joda.time.DateTime;

import uk.org.heswallcamp.handbook.data.model.ProgrammeEntry;
import uk.org.heswallcamp.handbook.data.model.ProgrammeEntry_;
import uk.org.heswallcamp.handbook.data.model.Year;
import uk.org.heswallcamp.handbook.data.model.Year_;

@Singleton
public class ProgrammeDAO {

	@Inject Provider<EntityManager> em;

	public List<ProgrammeEntry> getProgramme(Integer year) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<ProgrammeEntry> cq = cb.createQuery(ProgrammeEntry.class);

		Root<ProgrammeEntry> entry = cq.from(ProgrammeEntry.class);
		Join<ProgrammeEntry, Year> yearEntity = entry.join(ProgrammeEntry_.year);
		cq.where(cb.equal(yearEntity.get(Year_.year), year));
		cq.select(entry);
		return em.get().createQuery(cq).getResultList();
	}

	public void save(ProgrammeEntry entry) {
		em.get().persist(entry);
	}

	public void delete(DateTime id) {
		em.get().remove(em.get().find(ProgrammeEntry.class, id));
	}

}
