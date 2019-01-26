package br.edu.fatec.main.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.service.UserService;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
	
	Logger log = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		SecurityPrincipal principal = null;
		Optional<UserModel> user = null;
		if (authentication.getPrincipal() instanceof SecurityPrincipal) {
			principal = (SecurityPrincipal)authentication.getPrincipal(); 
			user = Optional.of(principal.getUser());
		} else {
			String email = authentication.getName();
			String password = authentication.getCredentials().toString();

			user = userService.findByEmail(email);
			if (!user.isPresent()) {
				throw new AuthenticationCredentialsNotFoundException(email);
			}

			validatePassword(password, user.get());
			
			principal = new SecurityPrincipal(user.get());
		}
		
		return new UsernamePasswordAuthenticationToken(principal, null);
	}

	private void validatePassword(String password, UserModel user) {
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException(user.getName());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}