package br.edu.fatec.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.persistency.UserRepository;
import br.edu.fatec.main.transients.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserModel save(UserModel user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public void delete(UserModel user) {
		userRepository.delete(user);
	}

	public Optional<UserModel> findById(String id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> findUserById(String id) {
		return userRepository.findUserById(id);
	}
	
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserModel> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public Optional<UserModel> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
