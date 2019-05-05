package br.edu.fatec.main.controllers.core;

import java.util.List;
import java.util.Optional;

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

import br.edu.fatec.main.controllers.authentication.AuthenticationSecurityPrincipal;
import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.model.SecurityPrincipal;
import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.service.AnnotationService;
import br.edu.fatec.main.service.UserService;
import br.edu.fatec.main.transients.Annotation;

@RestController
@RequestMapping(value = "/v1/annotation")
public class AnnotationController extends AuthenticationSecurityPrincipal {
	
	@Autowired
	private AnnotationService annotationService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
    public ResponseEntity<List<Annotation>> findAnnotationByUser(HttpServletRequest request) {
		SecurityPrincipal user = getSecurityPrincipal();
		return new ResponseEntity<>(annotationService.findAnnotationByUserId(user.getUser().getId()), HttpStatus.OK);
    }
	
	@GetMapping("{annotationId}")
    public ResponseEntity<Annotation> findAnnotationById(@PathVariable String annotationId, HttpServletRequest request) {
		SecurityPrincipal user = getSecurityPrincipal();
		Optional<Annotation> oAnnotation = annotationService.findAnnotationByIdAndUserId(annotationId, user.getUser().getId());
		if(oAnnotation.isPresent())
			return new ResponseEntity<>(oAnnotation.get(), HttpStatus.OK); 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@PostMapping
	public ResponseEntity<Annotation> save(@RequestBody AnnotationModel annotation, HttpServletRequest request) {
		SecurityPrincipal user = getSecurityPrincipal();
		Optional<UserModel> oUserModel = userService.findById(user.getUser().getId());
		if(oUserModel.isPresent()) {
			annotation.setUser(oUserModel.get());
			return new ResponseEntity<>(annotationService.saveAnnotation(annotation), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("{annotationId}")
	public ResponseEntity<Annotation> update(@PathVariable String annotationId, @RequestBody AnnotationModel annotation, HttpServletRequest request) {
		SecurityPrincipal user = getSecurityPrincipal();
		Optional<AnnotationModel> oAnnotation = annotationService.findByIdAndUserId(annotationId, user.getUser().getId());
		if(oAnnotation.isPresent()) {
			annotation.setId(oAnnotation.get().getId());
			annotation.setUser(oAnnotation.get().getUser());
			return new ResponseEntity<>(annotationService.saveAnnotation(annotation), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("{annotationId}")
	public ResponseEntity<Annotation> delete(@PathVariable String annotationId, HttpServletRequest request){
		SecurityPrincipal user = getSecurityPrincipal();
		if(annotationService.findByIdAndUserId(annotationId, user.getUser().getId()).isPresent()) {
			annotationService.delete(annotationService.findById(annotationId).get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
