package br.edu.fatec.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.edu.fatec.main.enumeration.Theme;

@Document(collection = "article")
public class ArticleModel {
	
	@Id
	private String id;
	private String title;
	private Theme theme;
	private String article; 
	
	public ArticleModel() {}
	
	public ArticleModel(String id, String title, Theme theme, String article) {
		this.id = id;
		this.title = title;
		this.theme = theme;
		this.article = article;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
}
