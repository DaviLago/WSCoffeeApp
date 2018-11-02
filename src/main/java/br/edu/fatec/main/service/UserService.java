package br.edu.fatec.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.persistency.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserModel save(UserModel user) {
		return userRepository.save(user);
	}

	public void delete(UserModel user) {
		userRepository.delete(user);
	}

	public Optional<UserModel> findById(String id) {
		return userRepository.findById(id);
	}

	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserModel> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
}
