package br.edu.fatec.main.controllers.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.service.AnnotationService;
import br.edu.fatec.main.service.UserService;

@RestController
@RequestMapping(value = "/v1/annotation")
public class AnnotationController {
	
	@Autowired
	private AnnotationService annotationService;
	
	@Autowired
	private UserService userService;
	
//	@Deprecated
//	@GetMapping("/{userId}/annotation") 
//    public ResponseEntity<List<AnnotationModel>> findByUserId(@PathVariable String userId) {
//		return new ResponseEntity<>(annotationService.findByUserId(userId), HttpStatus.OK);
//    }
	
	@GetMapping 
    public ResponseEntity<List<AnnotationModel>> findAnnotationByUser(HttpServletRequest request) {
		SecurityPrincipal user = (SecurityPrincipal) request.getUserPrincipal();
		return new ResponseEntity<>(annotationService.findByUserId(user.getUser().getId()), HttpStatus.OK);
    }
	
//	@Deprecated
//	@PostMapping("/{userId}/annotation")
//	public ResponseEntity<AnnotationModel> saveD(@PathVariable String userId, @RequestBody AnnotationModel annotation) {
//		if(userService.findById(userId).isPresent())
//			return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.CREATED);
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
	@PostMapping
	public ResponseEntity<AnnotationModel> save(@RequestBody AnnotationModel annotation, HttpServletRequest request) {
		SecurityPrincipal user = (SecurityPrincipal) request.getUserPrincipal();
		if(userService.findById(user.getUser().getId()).isPresent())
			return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
//	@Deprecated
//	@PutMapping("/{userId}/annotation/{annotationId}")
//	public ResponseEntity<AnnotationModel> updateD(@PathVariable String userId, @PathVariable String annotationId, @RequestBody AnnotationModel annotation) {
//		if(annotationService.findByIdAndUserId(annotationId, userId).isPresent()) {
//			annotation.setId(annotationId);
//			annotationService.save(annotation);
//			return new ResponseEntity<>(annotationService.save(annotation), HttpStatus.OK);
//		}
//		return new ResponseEntity<AnnotationModel>(new AnnotationModel(), HttpStatus.BAD_REQUEST);
//	}
	
	@PutMapping("{annotationId}")
	public ResponseEntity<AnnotationModel> update(@PathVariable String annotationId, @RequestBody AnnotationModel annotation, HttpServletRequest request) {
		SecurityPrincipal user = (SecurityPrincipal) request.getUserPrincipal();
		if(annotationService.findByIdAndUserId(annotationId, user.getUser().getId()).isPresent()) {
			annotation.setId(annotationId);
			annotationService.save(annotation);
			return new ResponseEntity<>(annotationService.save(annotation), HttpStatus.OK);
		}
		return new ResponseEntity<AnnotationModel>(new AnnotationModel(), HttpStatus.BAD_REQUEST);
	}
	
//	@Deprecated
//	@DeleteMapping("/{userId}/annotation/{annotationId}")
//	public ResponseEntity<AnnotationModel> deleteD(@PathVariable String userId, @PathVariable String annotationId){
//		if(annotationService.findByIdAndUserId(annotationId, userId).isPresent()) {
//			annotationService.delete(annotationService.findById(annotationId).get());
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
	@DeleteMapping("{annotationId}")
	public ResponseEntity<AnnotationModel> delete(@PathVariable String annotationId, HttpServletRequest request){
		SecurityPrincipal user = (SecurityPrincipal) request.getUserPrincipal();
		if(annotationService.findByIdAndUserId(annotationId, user.getUser().getId()).isPresent()) {
			annotationService.delete(annotationService.findById(annotationId).get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
