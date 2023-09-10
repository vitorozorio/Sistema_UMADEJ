package br.com.UMADEJ.dao;
import br.com.UMADEJ.model.Jovem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JovemDAO {
    private Connection connection;

    public JovemDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para salvar um objeto Jovem no banco de dados
    public void salvar(Jovem jovem) throws SQLException {
        String sql = "INSERT INTO tabela_jovens (nome, idade, dadosDoJovem) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jovem.getNome());
            statement.setString(2, jovem.getIdade());
            statement.setString(3, jovem.getDadosDoJovem());
            statement.executeUpdate();
        }
    }

    // Método para atualizar um objeto Jovem no banco de dados
    public void atualizar(Jovem jovem) throws SQLException {
        String sql = "UPDATE tabela_jovens SET nome=?, idade=?, dadosDoJovem=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jovem.getNome());
            statement.setString(2, jovem.getIdade());
            statement.setString(3, jovem.getDadosDoJovem());
            statement.setInt(4, jovem.getId());
            statement.executeUpdate();
        }
    }

    // Método para excluir um objeto Jovem do banco de dados por ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM tabela_jovens WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método para buscar um objeto Jovem por ID no banco de dados
    public Jovem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tabela_jovens WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Jovem jovem = new Jovem();
                    jovem.setId(resultSet.getInt("id"));
                    jovem.setNome(resultSet.getString("nome"));
                    jovem.setIdade(resultSet.getString("idade"));
                    jovem.setDadosDoJovem(resultSet.getString("dadosDoJovem"));
                    return jovem;
                }
            }
        }
        return null; // Retorna null se o jovem não for encontrado
    }

    // Método para listar todos os objetos Jovem no banco de dados
    public List<Jovem> listarTodos() throws SQLException {
        List<Jovem> jovens = new ArrayList<>();
        String sql = "SELECT * FROM tabela_jovens";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Jovem jovem = new Jovem();
                jovem.setId(resultSet.getInt("id"));
                jovem.setNome(resultSet.getString("nome"));
                jovem.setIdade(resultSet.getString("idade"));
                jovem.setDadosDoJovem(resultSet.getString("dadosDoJovem"));
                jovens.add(jovem);
            }
        }
        return jovens;
    }
}


