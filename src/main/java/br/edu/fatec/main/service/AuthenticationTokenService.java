package br.edu.fatec.main.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fatec.main.config.AuthSuccessHandler;
import br.edu.fatec.main.model.AuthenticationToken;
import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.persistency.AuthenticationTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
@Transactional
public class AuthenticationTokenService {
	
	Logger log = LoggerFactory.getLogger(AuthSuccessHandler.class);
	
	private static final SignatureAlgorithm SIGNATUREALGORITHM = SignatureAlgorithm.HS512;

	@Autowired
	private AuthenticationTokenRepository authenticationTokenRepository;

	private Long expiration = 86400L; // 24hrs em segundos

//	@Scheduled(cron = "0 0/5 * * * ?")
//	protected void cleanExpiredToken() {
//		authenticationTokenRepository.deleteExpiredTokens(new Date());
//	}

	public String generateToken(SecurityPrincipal principal) {

		final String tokenId = UUID.randomUUID().toString();

		log.info("Generating Token for UserId: {}, Name: {}, TokenId: {}", principal.getUser().getId(), principal.getUser().getName(), tokenId);

		Clock clock = DefaultClock.INSTANCE;
		SecretKey secretKey = MacProvider.generateKey(SIGNATUREALGORITHM);
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		Map<String, Object> claims = new HashMap<>();
		String token = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).setId(tokenId).setSubject(principal.getUser().getId()).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SIGNATUREALGORITHM, secretKey.getEncoded()).compact();

		AuthenticationToken authenticationToken = new AuthenticationToken();
		authenticationToken.setToken(token);
		authenticationToken.setTokenId(tokenId);
		authenticationToken.setTokenKey(secretKey.getEncoded());
		authenticationToken.setAlgorithm(secretKey.getAlgorithm());
		authenticationToken.setUser(principal.getUser());
		authenticationToken.setExpirationDate(expirationDate);
		save(authenticationToken);

		System.out.println("Generated Token");
		return token;
	}

	@SuppressWarnings("rawtypes")
	public UserModel validateToken(String authToken) throws Exception {
		String authTokenUnsec = StringUtils.substring(authToken, 0, StringUtils.lastIndexOf(authToken, ".") + 1);
		Jwt<Header, Claims> parsedUnsec = Jwts.parser().parseClaimsJwt(authTokenUnsec);

		String tokenId = parsedUnsec.getBody().getId();
		if (StringUtils.isBlank(tokenId))
			throw new Exception("InvalidTokenException");

		AuthenticationToken authenticationToken = authenticationTokenRepository.findOneByTokenId(tokenId);
		if (authenticationToken == null)
			throw new Exception("InvalidTokenException");

		// Verify that the token is properly signed
		Jwts.parser().setSigningKey(authenticationToken.getTokenKey()).parseClaimsJws(authToken);

		return authenticationToken.getUser();
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration * 1000);
	}

	private void save(AuthenticationToken authenticationToken) {
		authenticationTokenRepository.save(authenticationToken);
	}

}
