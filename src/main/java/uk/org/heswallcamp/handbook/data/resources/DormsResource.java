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

import uk.org.heswallcamp.handbook.data.dao.DormDAO;
import uk.org.heswallcamp.handbook.data.model.Dorm;
import uk.org.heswallcamp.handbook.data.model.DormAllocation;

@Singleton
@Path("/dorms")
@Produces(MediaType.APPLICATION_JSON)
public class DormsResource {

	@Inject DormDAO dao;
	
	@GET
    public List<Dorm> getDorms() {
    	return dao.getDorms();
    }
	
	@GET
	@Path("/{year}")
    public List<DormAllocation> getDorms(@PathParam("year") Integer year) {
    	return dao.getAllocations(year);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{year}")
	public void setAllocation(DormAllocation allocation) {
		dao.storeAllocation(allocation);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void setDorm(Dorm d) {
		dao.addDorm(d);
	}
	
}
