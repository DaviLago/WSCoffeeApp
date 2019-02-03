package br.edu.fatec.main.controllers.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatec.main.service.UserService;
import br.edu.fatec.main.transients.User;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable String userId) {
		Optional<User> oUser = userService.findUserById(userId);
		if(oUser.isPresent())
			return new ResponseEntity<>(oUser.get(), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
}
