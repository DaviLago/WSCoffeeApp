package br.edu.fatec.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.edu.fatec.main.service.AuthenticationTokenService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UnauthorizedAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private SecurityAuthenticationProvider authenticationProvider;

	@Autowired
	private AuthenticationTokenService authenticationTokenService;

	private String cerberonTokenHeader = "Authorization";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
    	auth
    		.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/auth/**", "/auth").permitAll()
            .anyRequest()
            .authenticated();

        AuthorizationTokenFilter authenticationTokenFilter = new AuthorizationTokenFilter(cerberonTokenHeader, authenticationTokenService);
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                "/auth", "/auth/**"
            )
            .antMatchers(
    			"/v2/api-docs", 
    			"/configuration/ui", 
    			"/swagger-resources/**", 
    			"/configuration/security", 
    			"/swagger-ui.html", 
    			"/webjars/**",
    			"/csrf",
    			"/",
    			"/springfox/**",
    			"/version",
    			"/sample");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

}
