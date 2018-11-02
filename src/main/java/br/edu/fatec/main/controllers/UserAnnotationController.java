package br.edu.fatec.main.controllers;

import java.util.List;

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

import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.service.AnnotationService;
import br.edu.fatec.main.service.UserService;

@RestController
@RequestMapping(value = "/v1/user")
public class UserAnnotationController {
	
	@Autowired
	private AnnotationService annotationService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}/annotation") 
    public ResponseEntity<List<AnnotationModel>> findByUserId(@PathVariable String userId) {
		return new ResponseEntity<List<AnnotationModel>>(annotationService.findByUserId(userId), HttpStatus.OK);
    }
	
	@PostMapping("/{userId}/annotation")
	public ResponseEntity<AnnotationModel> save(@PathVariable String userId, @RequestBody AnnotationModel annotation) {
		if(userService.findById(userId).isPresent())
			return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.CREATED);
		return new ResponseEntity<AnnotationModel>(new AnnotationModel(), HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/{userId}/annotation/{annotationId}")
	public ResponseEntity<AnnotationModel> update(@PathVariable String userId, @PathVariable String annotationId, @RequestBody AnnotationModel annotation) {
		
		if(annotationService.findByIdAndUserId(annotationId, userId).isPresent()) {
			System.out.println("ok");
			annotation.setId(annotationId);
			annotationService.save(annotation);
			return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.OK);
		}
		System.out.println("wrong");
		return new ResponseEntity<AnnotationModel>(new AnnotationModel(), HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/{userId}/annotation/{annotationId}")
	public ResponseEntity<AnnotationModel> delete(@PathVariable String userId, @PathVariable String annotationId){
		if(annotationService.findByIdAndUserId(annotationId, userId).isPresent()) {
			annotationService.delete(annotationService.findById(annotationId).get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}
