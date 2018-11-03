package br.edu.fatec.main.persistency;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatec.main.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String>{

	Optional<UserModel> findByEmailAndPassword(String email, String password);

	Optional<UserModel> findByEmail(String email);

}
