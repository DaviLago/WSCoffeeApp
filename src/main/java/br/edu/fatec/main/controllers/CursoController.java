package br.edu.fatec.main.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class CursoController {
	
//	@Autowired
//	private CursoRepository repository;
//	
//	private Map<Integer, Curso> cursos;
//	 
//	public CursoController() {
//		cursos = new HashMap<Integer, Curso>();
//	    Curso c1 = new Curso("Workshop Rest", "24hs");
//	    Curso c2 = new Curso("Workshop Spring MVC", "24hs");
//	    Curso c3 = new Curso("Desenvolvimento Web com JSF 2", "60hs");
//	 
//	    cursos.put(1, c1);
//	    cursos.put(2, c2);
//	    cursos.put(3, c3);
//	}
//	 
//	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
//	public ResponseEntity<List<Curso>> listar() {
//		repository.deleteAll();
//		repository.saveAll(new ArrayList<Curso>(cursos.values()));
//		return new ResponseEntity<List<Curso>>(repository.findAll(), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/cursos/{nome}", method = RequestMethod.GET)
//	public ResponseEntity<Curso> findByNome(@PathVariable("nome") String nome) {
//		return new ResponseEntity<Curso>(repository.findByNome(nome), HttpStatus.OK);
//	}
}
