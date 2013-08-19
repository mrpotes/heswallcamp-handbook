package uk.org.heswallcamp.handbook.data;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class BoysServlet extends HttpServlet {

    @Inject BoysDAO boysDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if (req.getPathInfo().matches("/data/boys")) {
    		
    	} else {
    		
    	}
    }

}
