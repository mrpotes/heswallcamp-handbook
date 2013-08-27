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

import uk.org.heswallcamp.handbook.data.model.KitchenRota;
import uk.org.heswallcamp.handbook.data.model.KitchenRota_;
import uk.org.heswallcamp.handbook.data.model.MealTime;
import uk.org.heswallcamp.handbook.data.model.MealTime_;
import uk.org.heswallcamp.handbook.data.model.Year;
import uk.org.heswallcamp.handbook.data.model.Year_;

@Singleton
public class MealsDAO {

	@Inject Provider<EntityManager> em;

	public List<MealTime> getMeals(Integer y) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<MealTime> cq = cb.createQuery(MealTime.class);

		Root<MealTime> mealTime = cq.from(MealTime.class);
		Join<MealTime, Year> year = mealTime.join(MealTime_.year);
		cq.where(cb.equal(year.get(Year_.year), y));
		cq.select(mealTime);
		return em.get().createQuery(cq).getResultList();
	}

	public List<KitchenRota> getRota(Integer y) {
		CriteriaBuilder cb = em.get().getCriteriaBuilder();
		CriteriaQuery<KitchenRota> cq = cb.createQuery(KitchenRota.class);

		Root<KitchenRota> rota = cq.from(KitchenRota.class);
		Join<MealTime, Year> year = rota.join(KitchenRota_.mealTime).join(MealTime_.year);
		cq.where(cb.equal(year.get(Year_.year), y));
		cq.select(rota);
		return em.get().createQuery(cq).getResultList();
	}

	public void save(MealTime mt) {
		em.get().persist(mt);
	}

	public void save(KitchenRota rota) {
		em.get().persist(rota);
	}

}
