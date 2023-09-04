package TESTES;
import java.util.Date;

import br.com.UMADEJ.model.Jovem;
import br.com.UMADEJ.dao.JovemDAO;

public class Teste {

	public static void main(String[] args) throws Exception {
		
		
		JovemDAO jovemdao = new JovemDAO();
		
		Jovem jovem = new Jovem();
		
		jovem.setNome("Vitor Manoel Basilio Ozorio");
		jovem.setDadosDoJovem("Desenvolvedor do sistema");
		jovem.setDataNascimento("20/02/2002");
		jovem.setDataCadastro(new Date());
		
		jovemdao.save(jovem);
		
	}
	
	
}
