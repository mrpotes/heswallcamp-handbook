package uk.org.heswallcamp.handbook.data.resources;

import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.joda.time.LocalDate;

import uk.org.heswallcamp.handbook.data.dao.YearDAO;
import uk.org.heswallcamp.handbook.data.model.Year;

import com.google.inject.Inject;

@Singleton
@Path("/camp")
@Produces(MediaType.APPLICATION_JSON)
public class YearsResource {
	
	@Inject YearDAO yearDAO;

	@POST
	@Path("/create")
    public void createYear(
    		@FormParam("year") Integer year, 
    		@FormParam("starts") String starts, 
    		@FormParam("shiftLength") Integer shiftLength) {
		Year y = new Year();
		y.setYear(year);
		persist(y, starts);
	}
	
	@POST
	@Path("/{year}")
    public void updateYear(
    		@PathParam("year") Integer year, 
    		@FormParam("starts") String starts) {
    	Year y = yearDAO.getYear(year);
    	persist(y, starts);
    }
	
	private void persist(Year y, String starts) {
		y.setStartDate(LocalDate.parse(starts));
		yearDAO.save(y);
	}

	
}
