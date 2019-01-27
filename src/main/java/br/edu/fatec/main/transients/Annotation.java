package br.edu.fatec.main.transients;

public class Annotation {
	
	private String id;
	private String title;
	private String cafeteria;
	private String cafe;
	private String barista;
	private String harmonizacao;
	private String complemento;
	private String metodoPreparo;
	private String descricao;
	
	public Annotation() {
	}
	
	public Annotation(String title, String cafeteria, String cafe, String barista, String harmonizacao, String complemento,
			String metodoPreparo, String descricao) {
		this.title = title;
		this.cafeteria = cafeteria;
		this.cafe = cafe;
		this.barista = barista;
		this.harmonizacao = harmonizacao;
		this.complemento = complemento;
		this.metodoPreparo = metodoPreparo;
		this.descricao = descricao;
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

	public String getCafeteria() {
		return cafeteria;
	}
	
	public void setCafeteria(String cafeteria) {
		this.cafeteria = cafeteria;
	}
	
	public String getCafe() {
		return cafe;
	}
	
	public void setCafe(String cafe) {
		this.cafe = cafe;
	}
	
	public String getBarista() {
		return barista;
	}
	
	public void setBarista(String barista) {
		this.barista = barista;
	}
	
	public String getHarmonizacao() {
		return harmonizacao;
	}
	
	public void setHarmonizacao(String harmonizacao) {
		this.harmonizacao = harmonizacao;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getMetodoPreparo() {
		return metodoPreparo;
	}
	
	public void setMetodoPreparo(String metodoPreparo) {
		this.metodoPreparo = metodoPreparo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toSring() {
		return "cafeteria: " + this.cafeteria + ", cafe: " + this.cafe + ", barista: " + this.barista + ", harmonizacao: " + this.harmonizacao +
			   ", complemento: " + this.complemento + ", metodoPreparo: " + this.metodoPreparo + ", descricao: " + this.descricao;
	}
	
}
