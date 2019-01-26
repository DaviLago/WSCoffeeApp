package br.edu.fatec.main.controllers.authentication;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatec.main.model.AuthenticationRequest;
import br.edu.fatec.main.model.AuthenticationResponse;
import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.service.AuthenticationTokenService;
import br.edu.fatec.main.service.UserService;

@RestController
public class AuthenticationController {

	Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationTokenService authenticationTokenService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@PostMapping(path = { "/auth" })
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		Authentication auth = null;

		if (StringUtils.isBlank(authenticationRequest.getEmail()) || StringUtils.isBlank(authenticationRequest.getPassword())) {
			throw new Exception("err.authentication.invalid.userpass");
		}

		auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

		final String token = authenticationTokenService.generateToken((SecurityPrincipal) auth.getPrincipal());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@PostMapping(path = { "/auth/user" })
	public ResponseEntity<AuthenticationResponse> save(@RequestBody UserModel user) {
		if(user.getName() == null || user.getEmail() == null || user.getPassword() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else if(user.getName().trim().isEmpty() || user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		user.setName(user.getName().trim());
		user.setEmail(user.getEmail().trim());
		if(userService.findByEmail(user.getEmail()).isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		user = userService.save(user);
		Authentication auth = new UsernamePasswordAuthenticationToken(new SecurityPrincipal(user), null);
		final String token = authenticationTokenService.generateToken((SecurityPrincipal) auth.getPrincipal());
		return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.CREATED);
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getErrorMessageFromException(e));
	}

	private String getErrorMessageFromException(Exception exception) {
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			return "err.authentication.bad.credential";
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			return "err.authentication.disabled.account";
		} else if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {
			return "err.authentication.generic";
		} else if (exception.getClass().isAssignableFrom(LockedException.class)) {
			return "err.authentication.locked.account";
		} else if (exception.getClass().isAssignableFrom(InsufficientAuthenticationException.class)) {
			return "err.authentication.insufficient";
		} else if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			return "err.authentication.username.not.found";
		} else if (exception.getClass().isAssignableFrom(AuthenticationCredentialsNotFoundException.class)) {
			return "err.authentication.credential.not.found";
//		} else if (exception.getClass().isAssignableFrom(ExpiredLicenseException.class)) {
//			return "err.license.expired";
		} else {
			return "err.authentication.generic";
		}
	}
}
