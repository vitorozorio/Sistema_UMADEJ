package br.com.UMADEJ.model;


public class Jovem {

	private int id; 
	private String nome;
	private String idade;
	private String dadosDoJovem;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDadosDoJovem() {
		return dadosDoJovem;
	}
	public void setDadosDoJovem(String dadosDoJovem) {
		this.dadosDoJovem = dadosDoJovem;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
}
