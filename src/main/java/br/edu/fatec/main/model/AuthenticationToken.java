package br.edu.fatec.main.model;

import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "token")
public class AuthenticationToken {

	@Id
	private String id;
	private String tokenId;
	private String token;
	private String tokenKey = null;
	private String algorithm = null;
	private Date expirationDate = null;
	@DBRef
	private UserModel user;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public byte[] getTokenKey() {
		return Base64.decodeBase64(tokenKey);
	}

	public void setTokenKey(byte[] tokenKey) {
		this.tokenKey = Base64.encodeBase64String(tokenKey);
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
}
