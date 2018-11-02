package br.edu.fatec.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatec.main.enumeration.Theme;
import br.edu.fatec.main.model.ArticleModel;
import br.edu.fatec.main.persistency.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	public ArticleModel save(ArticleModel article) {
		return articleRepository.save(article);
	}

	public void delete(ArticleModel article) {
		articleRepository.delete(article);
	}

	public Optional<ArticleModel> findById(String id) {
		return articleRepository.findById(id);
	}

	public List<ArticleModel> findAll() {
		return articleRepository.findAll();
	}
	
	public List<ArticleModel> findAllByTheme(Theme theme) {
		return articleRepository.findAllByTheme(theme);
	}
	
}
