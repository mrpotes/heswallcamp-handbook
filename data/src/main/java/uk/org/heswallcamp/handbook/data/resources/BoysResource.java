package uk.org.heswallcamp.handbook.data.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.org.heswallcamp.handbook.data.dao.BoysDAO;
import uk.org.heswallcamp.handbook.data.model.Boy;

import com.google.appengine.api.datastore.KeyFactory;

@Singleton
@Path("boys")
@Produces(MediaType.APPLICATION_JSON)
public class BoysResource {

	@Inject BoysDAO dao;
	
	@GET
	@Path("/{id}")
    public Boy getBoy(@PathParam("id") String id) {
    	return dao.getBoy(KeyFactory.stringToKey(id));
    }
	
	@POST
	public void setBoy(Boy boy) {
		dao.save(boy);
	}
	
}
