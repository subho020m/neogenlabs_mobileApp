package com.neogenlabs.filter.response.cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sling Filter for CORS header in response
 * @author AMIT
 */
@SlingFilter(order=1, description="Sling Filter to add CORS header to all responses")
@Property(name="service.vendor", value="NeoGen Labs")
public class SlingCORSFilter implements Filter {
    
    private final Logger log = LoggerFactory.getLogger(SlingCORSFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
    	
    		HttpServletRequest req = (HttpServletRequest) request;
    		HttpServletResponse res = (HttpServletResponse) response;
    		
        log.info("filter invoked - start");
        
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST");
        res.setHeader("Access-Control-Max-Age", "360");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        chain.doFilter(request, response);
        
        log.info("filter invoked - end");
    }

    public void destroy() {
    }

}
