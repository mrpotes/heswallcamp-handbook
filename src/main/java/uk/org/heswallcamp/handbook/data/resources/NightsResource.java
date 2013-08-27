package uk.org.heswallcamp.handbook.data.resources;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.org.heswallcamp.handbook.data.dao.NightShiftDAO;
import uk.org.heswallcamp.handbook.data.model.NightShift;
import uk.org.heswallcamp.handbook.data.model.NightShiftAllocation;

@Singleton
@Path("/nights")
@Produces(MediaType.APPLICATION_JSON)
public class NightsResource {

	@Inject NightShiftDAO dao;
	
	@GET
	@Path("/{year}")
    public Map<NightShift, List<NightShiftAllocation>> getBoy(@PathParam("year") Integer year) {
    	return dao.getShifts(year);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void getBoys(NightShiftAllocation allocation) {
    	dao.save(allocation);
    }
	
}
