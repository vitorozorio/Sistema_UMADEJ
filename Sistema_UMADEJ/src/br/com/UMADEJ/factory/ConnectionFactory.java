package br.com.UMADEJ.factory;
import java.sql.Connection; 
import java.sql.DriverManager;

public class ConnectionFactory {
	
	// Nome do usuário do MySQL
	private static final String USERNAME = "root";
	
	// Senha do banco
	private static final String PASSWORD = "";
	
	// Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/umadej";
	
	/*
	 * Classe responsável por criar conexões com o banco de dados MySQL.
	 * Ela fornece um método estático para criar uma conexão usando as informações de configuração definidas.
	 */
	
	/*
	 * Cria uma conexão com o banco de dados MySQL.
	 * Retorna uma instância de Connection que representa a conexão estabelecida.
	 * Lança uma exceção caso ocorra algum erro durante o processo de conexão.
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		// Faz com que a classe de driver do MySQL seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver"); // novo formato do nome do driver da JVM 
		
		// Cria a conexão com o banco de dados usando as informações fornecidas
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
		
	public static void main(String[] args) throws Exception {
		
		// Recupera uma conexão com o banco de dados
		Connection con = createConnectionToMySQL();
		
		// Testa se a conexão é nula
		if (con != null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
	}
	
}