package uk.org.heswallcamp.handbook.data.resources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;

import uk.org.heswallcamp.handbook.data.dao.ProgrammeDAO;
import uk.org.heswallcamp.handbook.data.model.ProgrammeEntry;

@Singleton
@Path("/programme")
@Produces(MediaType.APPLICATION_JSON)
public class ProgrammeResource {

	@Inject ProgrammeDAO dao;
	
	@GET
	@Path("/{year}")
    public List<ProgrammeEntry> getBoy(@PathParam("year") Integer year) {
    	return dao.getProgramme(year);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void save(ProgrammeEntry entry) {
    	dao.save(entry);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") Long idMillis) {
		DateTime dt = new DateTime(idMillis);
    	dao.delete(dt);
    }
	
}
