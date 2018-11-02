package br.edu.fatec.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatec.main.enumeration.Theme;
import br.edu.fatec.main.model.ArticleModel;
import br.edu.fatec.main.service.ArticleService;

@RestController
@RequestMapping(value = "/v1/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@GetMapping
	public ResponseEntity<List<ArticleModel>> findAll() {
		return new ResponseEntity<List<ArticleModel>>(articleService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/theme/{theme}")
	public ResponseEntity<List<ArticleModel>> findAllByTheme(@PathVariable Theme theme) {
		return new ResponseEntity<List<ArticleModel>>(articleService.findAllByTheme(theme), HttpStatus.OK);
	}
	
	@GetMapping("/{articleid}")
    public ResponseEntity<ArticleModel> findById(@PathVariable String articleid) {
        return new ResponseEntity<ArticleModel>(articleService.findById(articleid).get(), HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<ArticleModel> save(@RequestBody ArticleModel article) {
		articleService.save(article);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{articleid}")
	public ResponseEntity<ArticleModel> update(@PathVariable String articleid, @RequestBody ArticleModel article) {
		article.setId(articleid);
		articleService.save(article);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{articleid}")
	public ResponseEntity<ArticleModel> delete(@PathVariable String articleid){
		articleService.delete(articleService.findById(articleid).get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ArticleModel> postCreating(){

		ArticleModel a = new ArticleModel();
		a.setTheme(Theme.TORRA_CLARA);
		a.setTitle("Torra Clara1");
		a.setArticle("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?");
		
		ArticleModel b = new ArticleModel();
		b.setTheme(Theme.TORRA_CLARA);
		b.setTitle("Torra Clara2");
		b.setArticle("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?");
		
		ArticleModel c = new ArticleModel();
		c.setTheme(Theme.TORRA_ESCURA);
		c.setTitle("Torra Escura");
		c.setArticle("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?");
		
		ArticleModel d = new ArticleModel();
		d.setTheme(Theme.TORRA_MEDIA);
		d.setTitle("Torra Media");
		d.setArticle("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?");
		
		articleService.save(a);
		articleService.save(b);
		articleService.save(c);
		articleService.save(d);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
