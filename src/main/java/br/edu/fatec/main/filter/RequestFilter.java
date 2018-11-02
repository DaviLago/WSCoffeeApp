package br.edu.fatec.main.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.edu.fatec.main.service.UserService;

@Component
public class RequestFilter implements Filter {

	Logger log = LoggerFactory.getLogger(RequestFilter.class);
	
	@Autowired
	UserService userService;
	
	@Bean
	public FilterRegistrationBean<RequestFilter> loggingFilter(){
	    FilterRegistrationBean<RequestFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	         
	    registrationBean.setFilter(new RequestFilter());
	    registrationBean.addUrlPatterns("/v1/user/*");
	         
	    return registrationBean;    
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        log.info(
          "Logging Request  {} : {}", req.getHeader("userId"), 
          req.getRequestURI());
        chain.doFilter(request, response);
        log.info(
          "Logging Response :{}", 
          res.getStatus());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
