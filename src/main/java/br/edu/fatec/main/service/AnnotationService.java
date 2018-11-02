package br.edu.fatec.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.persistency.AnnotationRepository;

@Service
public class AnnotationService {
	
	@Autowired
	AnnotationRepository annotationRepository;

	public AnnotationModel save(AnnotationModel annotation) {
		return annotationRepository.save(annotation);
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

	public Optional<AnnotationModel> findByIdAndUserId(String annotationId, String userId) {
		return annotationRepository.findByIdAndUserId(annotationId, userId);
	}
	
	
}
