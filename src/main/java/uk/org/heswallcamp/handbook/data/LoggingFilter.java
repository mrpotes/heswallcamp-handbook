package uk.org.heswallcamp.handbook.data;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter {
	
	private Logger logger = Logger.getLogger(LoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest)req;
			logger.info(httpServletRequest.getMethod() + " Request: "+httpServletRequest.getServletPath());
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

}
