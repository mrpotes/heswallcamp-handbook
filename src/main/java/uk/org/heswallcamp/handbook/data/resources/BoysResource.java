package uk.org.heswallcamp.handbook.data.resources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import uk.org.heswallcamp.handbook.data.dao.BoysDAO;
import uk.org.heswallcamp.handbook.data.model.Boy;

@Singleton
@Path("/boys")
@Produces(MediaType.APPLICATION_JSON)
public class BoysResource {

	@Inject BoysDAO dao;
	
	@GET
	@Path("/{id}")
    public Boy getBoy(@PathParam("name") String name) {
    	return dao.getBoy(name);
    }
	
	@GET
    public List<Boy> getBoys(@QueryParam("year") Integer year) {
    	return dao.getBoys(year);
    }
	
	@POST
	public void setBoy(Boy boy) {
		dao.save(boy);
	}
	
}
