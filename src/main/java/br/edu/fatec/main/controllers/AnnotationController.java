package br.edu.fatec.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.service.AnnotationService;

@RestController
@RequestMapping(value = "/v1/annotation")
public class AnnotationController {
	
	@Autowired
	private AnnotationService annotationService;
	
//	@GetMapping
//	public ResponseEntity<List<AnnotationModel>> findAll() {
//		return new ResponseEntity<List<AnnotationModel>>(annotationService.findAll(), HttpStatus.OK);
//	}
//
//	@GetMapping("/{annotationid}") 
//    public ResponseEntity<AnnotationModel> findById(@PathVariable String annotationid) {
//		return new ResponseEntity<AnnotationModel>(annotationService.findById(annotationid).get(), HttpStatus.OK);
//    }
	
//	@PostMapping
//	public ResponseEntity<AnnotationModel> save(@RequestBody AnnotationModel annotation) {
//		return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/{annotationid}")
//	public ResponseEntity<AnnotationModel> update(@PathVariable String annotationId, @RequestBody AnnotationModel annotation, HttpServletRequest request) {
//		
//		if(annotationService.findByIdAndUserId(annotationId, request.getHeader("userId")).isPresent()) {
//			annotation.setId(annotationId);
//			annotationService.save(annotation);
//			return new ResponseEntity<AnnotationModel>(annotationService.save(annotation), HttpStatus.CREATED);
//		}
//		return new ResponseEntity<AnnotationModel>(new AnnotationModel(), HttpStatus.UNAUTHORIZED);
//	}
//	
//	@DeleteMapping("/{annotationid}")
//	public ResponseEntity<AnnotationModel> delete(@PathVariable String annotationid){
//		annotationService.delete(annotationService.findById(annotationid).get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
}
