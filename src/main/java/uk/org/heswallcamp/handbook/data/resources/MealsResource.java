package uk.org.heswallcamp.handbook.data.resources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.org.heswallcamp.handbook.data.dao.MealsDAO;
import uk.org.heswallcamp.handbook.data.model.KitchenRota;
import uk.org.heswallcamp.handbook.data.model.MealTime;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class MealsResource {

	@Inject MealsDAO dao;
	
	@GET
	@Path("/kitchen-rota/{year}")
    public List<KitchenRota> getRota(@PathParam("year") Integer year) {
    	return dao.getRota(year);
    }
	
	@GET
	@Path("/meals/{year}")
    public List<MealTime> getMeals(@PathParam("year") Integer year) {
    	return dao.getMeals(year);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void allocateRota(KitchenRota rota) {
		dao.save(rota);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveMeal(MealTime meal) {
		dao.save(meal);
	}
}
