package br.edu.fatec.main.controllers.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/annotation")
public class AnnotationController {
	
//	@Autowired
//	private AnnotationService annotationService;
	
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
