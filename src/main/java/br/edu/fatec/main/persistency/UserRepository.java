package br.edu.fatec.main.persistency;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatec.main.model.UserModel;
import br.edu.fatec.main.transients.User;

public interface UserRepository extends MongoRepository<UserModel, String>{

	Optional<UserModel> findByEmailAndPassword(String email, String password);

	Optional<UserModel> findByEmail(String email);

	Optional<User> findUserById(String id);

}
