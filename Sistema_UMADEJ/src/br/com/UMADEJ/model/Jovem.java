package br.com.UMADEJ.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Jovem {

	private int id; 
	private String nome;
	private Date dataCadastro;
	private String dataNascimento;
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getDadosDoJovem() {
		return dadosDoJovem;
	}
	public void setDadosDoJovem(String dadosDoJovem) {
		this.dadosDoJovem = dadosDoJovem;
	}
	
	public void setDataCadastro(String dataCadastro) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dataCadastro = dateFormat.parse(dataCadastro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	public static void deleteByID(int i) {
		// TODO Auto-generated method stub
		
	}
	public static void update(Jovem jovem) {
		// TODO Auto-generated method stub
		
	}
	public static void save(Jovem jovem) {
		// TODO Auto-generated method stub
		
	}
	public static List<Jovem> getJovens() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
