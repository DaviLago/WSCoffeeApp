package br.edu.fatec.main.persistency;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatec.main.model.AnnotationModel;

public interface AnnotationRepository extends MongoRepository<AnnotationModel, String> {

	List<AnnotationModel> findByUserId(String userId);

	Optional<AnnotationModel> findByIdAndUserId(String annotationId, String userId);

}
