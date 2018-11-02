package br.edu.fatec.main.persistency;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatec.main.enumeration.Theme;
import br.edu.fatec.main.model.ArticleModel;

public interface ArticleRepository extends MongoRepository<ArticleModel, String>{
	
	public List<ArticleModel> findAllByTheme(Theme theme);

}
