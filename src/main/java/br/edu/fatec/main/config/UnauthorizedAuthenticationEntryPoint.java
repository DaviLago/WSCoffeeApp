package br.edu.fatec.main.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnauthorizedAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	Logger log = LoggerFactory.getLogger(UnauthorizedAuthenticationEntryPoint.class);

	private static final long serialVersionUID = 438745956396765673L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		log.info("Commence Method of Class UnauthorizedAuthenticationEntryPoint %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}

}
