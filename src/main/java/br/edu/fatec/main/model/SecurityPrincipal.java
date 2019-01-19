package br.edu.fatec.main.model;

import java.io.Serializable;

public class SecurityPrincipal implements Serializable {
	
	private static final long serialVersionUID = -3128227628590981252L;

	private UserModel user;

	public SecurityPrincipal(UserModel user) {
		super();
		this.setUser(user);
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
