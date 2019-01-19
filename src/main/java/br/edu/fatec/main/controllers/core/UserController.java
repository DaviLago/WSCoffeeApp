package br.edu.fatec.main.controllers.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{userId}")
    public ResponseEntity<UserModel> findById(@PathVariable String userId) {
		Optional<UserModel> oUser = userService.findById(userId);
		if(oUser.isPresent())
			return new ResponseEntity<>(userService.findById(userId).get(), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
//	@Deprecated
//	@GetMapping("/auth/{email}/{password}")
//    public ResponseEntity<UserModel> findByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
//		email = email.trim();
//		Optional<UserModel> oUser = userService.findByEmailAndPassword(email, password);
//		if(oUser.isPresent())
//			return new ResponseEntity<>(oUser.get(), HttpStatus.OK);
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserModel> update(@PathVariable String userId, @RequestBody UserModel user) {
		user.setId(userId);
		Optional<UserModel> oUser = userService.findById(userId);
		if(oUser.isPresent()) 
			return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserModel> delete(@PathVariable String userId){
		Optional<UserModel> oUser = userService.findById(userId);
		if(oUser.isPresent())
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
