package br.edu.fatec.main.controllers.core;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.fatec.main.model.SecurityPrincipal;

public class AuthenticationSecurityPrincipal {
	
	public SecurityPrincipal getSecurityPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return (SecurityPrincipal) auth.getPrincipal();
		}
		return null;
	}

}
