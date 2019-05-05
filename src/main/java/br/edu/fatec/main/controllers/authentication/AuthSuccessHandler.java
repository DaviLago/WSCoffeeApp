package br.edu.fatec.main.controllers.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
	
	Logger log = LoggerFactory.getLogger(AuthSuccessHandler.class);
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.error("Authorized {} %%%%%%%%%%%%%%%%%%%%%%%%%%%", authentication.toString());
    }

}
