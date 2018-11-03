package br.edu.fatec.main.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.service.UserService;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@GetMapping
//	public ResponseEntity<List<UserModel>> findAll() {
//		return new ResponseEntity<List<UserModel>>(userService.findAll(), HttpStatus.OK);
//	}

	@GetMapping("/{userId}") 
    public ResponseEntity<UserModel> findById(@PathVariable String userId) {
		return new ResponseEntity<UserModel>(userService.findById(userId).get(), HttpStatus.OK);
    }
	
	@GetMapping("/auth/{email}/{password}")
    public ResponseEntity<UserModel> findByEmailAndPassword(@PathVariable String email, @PathVariable String password) {

		email = email.trim();
		
		Optional<UserModel> user = userService.findByEmailAndPassword(email, password);
		if(user.isPresent())
			return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
		
		return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
    }
	
	@PostMapping
	public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
		if(user.getName() == null || user.getEmail() == null || user.getPassword() == null)
			return new ResponseEntity<UserModel>(user, HttpStatus.BAD_REQUEST);
		else if(user.getName().trim() == null || user.getEmail().trim() == null || user.getPassword().trim() == null)
			return new ResponseEntity<UserModel>(user, HttpStatus.BAD_REQUEST);
		
		user.setName(user.getName().trim());
		user.setEmail(user.getEmail().trim());
		
		if(userService.findByEmail(user.getEmail()).isPresent())
			return new ResponseEntity<UserModel>(user, HttpStatus.CONFLICT);
		
		return new ResponseEntity<UserModel>(userService.save(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserModel> update(@PathVariable String userId, @RequestBody UserModel user) {
		user.setId(userId);
		userService.save(user);
		return new ResponseEntity<UserModel>(userService.save(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserModel> delete(@PathVariable String userId){
		userService.delete(userService.findById(userId).get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
