package br.edu.fatec.main.controllers.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
}
