package br.edu.fatec.main.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.service.AuthenticationTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

public class AuthorizationTokenFilter extends OncePerRequestFilter {
	
	Logger log = LoggerFactory.getLogger(AuthorizationTokenFilter.class);
	
	private AuthenticationTokenService authenticationTokenService;
	private String tokenHeader;

	public AuthorizationTokenFilter(String cerberonTokenHeader, AuthenticationTokenService authenticationTokenService) {
		this.tokenHeader = cerberonTokenHeader;
		this.authenticationTokenService = authenticationTokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String requestHeader = request.getHeader(this.tokenHeader);
		final String authToken;
		final UserModel user;

		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			authToken = requestHeader.substring(7);
			try {
				user = authenticationTokenService.validateToken(authToken);

				if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					SecurityPrincipal cerberonPrincipal = new SecurityPrincipal(user);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cerberonPrincipal, "");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}

			} catch (/*InvalidTokenException | ExpiredTokenException |*/ ExpiredJwtException | MalformedJwtException e) {
				logger.warn("The token is invalid or expired and not valid anymore %%%%%%%%%%%%%%%%%%%%%%%%%%");
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else {
			logger.warn("Couldn't find bearer string, will ignore the header %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		}

		filterChain.doFilter(request, response);
	}
	
}
