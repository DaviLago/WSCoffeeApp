package br.edu.fatec.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.persistency.AnnotationRepository;
import br.edu.fatec.main.transients.Annotation;

@Service
public class AnnotationService {
	
	@Autowired
	private AnnotationRepository annotationRepository;

	public Annotation saveAnnotation(AnnotationModel annotationModel) {
		annotationModel = annotationRepository.save(annotationModel);
		Annotation annotation = new Annotation();
		BeanUtils.copyProperties(annotationModel, annotation);
		return annotation;
	}
	
	public void delete(AnnotationModel annotation) {
		annotationRepository.delete(annotation);
	}

	public Optional<AnnotationModel> findById(String id) {
		return annotationRepository.findById(id);
	}

	public List<AnnotationModel> findAll() {
		return annotationRepository.findAll();
	}

	public List<AnnotationModel> findByUserId(String userId) {
		return annotationRepository.findByUserId(userId);
	}
	
	public List<Annotation> findAnnotationByUserId(String userId) {
		return annotationRepository.findAnnotationByUserId(userId);
	}

	public Optional<AnnotationModel> findByIdAndUserId(String annotationId, String userId) {
		return annotationRepository.findByIdAndUserId(annotationId, userId);
	}
	
	public Optional<Annotation> findAnnotationByIdAndUserId(String annotationId, String userId) {
		return annotationRepository.findAnnotationByIdAndUserId(annotationId, userId);
	}
	
	
}
