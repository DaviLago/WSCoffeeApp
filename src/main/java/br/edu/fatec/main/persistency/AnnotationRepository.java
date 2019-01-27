package br.edu.fatec.main.persistency;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatec.main.model.AnnotationModel;
import br.edu.fatec.main.transients.Annotation;

public interface AnnotationRepository extends MongoRepository<AnnotationModel, String> {

	List<AnnotationModel> findByUserId(String userId);

	Optional<AnnotationModel> findByIdAndUserId(String annotationId, String userId);

	List<Annotation> findAnnotationByUserId(String userId);

	Optional<Annotation> findAnnotationByIdAndUserId(String annotationId, String userId);

}
