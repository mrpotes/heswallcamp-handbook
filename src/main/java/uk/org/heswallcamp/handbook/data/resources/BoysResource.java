package uk.org.heswallcamp.handbook.data.resources;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bouncycastle.openssl.PEMParser;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.org.heswallcamp.handbook.data.dao.BoysDAO;
import uk.org.heswallcamp.handbook.data.model.Boy;

@Singleton
@Path("/boys")
@Produces(MediaType.APPLICATION_JSON)
public class BoysResource {

	@Inject
	BoysDAO dao;

	@GET
	public Boy getBoy(@QueryParam("name") String name) {
		return dao.getBoy(name);
	}

	@GET
	@Path("/{year}")
	public List<Boy> getBoys(@PathParam("year") Integer year) {
		return dao.getBoys(year);
	}

	@POST
	public Response getBoysSecure(@FormParam("key") String key,
			@FormParam("year") Integer year) throws IOException {
		PEMParser reader = new PEMParser(new StringReader(key));
		try {
			reader.readObject();
			StringWriter w = new StringWriter();
			new ObjectMapper().writeValue(w, getBoys(year));
			String encrypted = w.toString();
			return Response.ok().entity(encrypted).build();
		} finally {
			reader.close();
		}
	}

	@POST
	public void setBoy(Boy boy) {
		dao.save(boy);
	}

}
