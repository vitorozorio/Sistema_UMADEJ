package br.com.UMADEJ.dao;

import br.com.UMADEJ.model.Jovem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.UMADEJ.factory.ConnectionFactory;

public class JovemDAO {
    // Método que salva um objeto Jovem no banco de dados
    public void salvar(Jovem jovem) throws SQLException {
        String sql = "INSERT INTO jovem (nome, idade, dadosDoJovem) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, jovem.getNome());
            stmt.setString(2, jovem.getIdade());
            stmt.setString(3, jovem.getDadosDoJovem());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método que atualiza um objeto Jovem no banco de dados
    public void atualizar(Jovem jovem) throws SQLException {
        String sql = "UPDATE jovem SET nome=?, idade=?, dadosDoJovem=? WHERE id=?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, jovem.getNome());
            stmt.setString(2, jovem.getIdade());
            stmt.setString(3, jovem.getDadosDoJovem());
            stmt.setInt(4, jovem.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método que exclui um objeto Jovem do banco de dados por ID
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM jovem WHERE id=?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return false;
    }
    

    // Método que busca um objeto Jovem por ID no banco de dados
    public Jovem buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM jovem WHERE id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Jovem jovem = new Jovem();
                jovem.setId(rs.getInt("id"));
                jovem.setNome(rs.getString("nome"));
                jovem.setIdade(rs.getString("idade"));
                jovem.setDadosDoJovem(rs.getString("dadosDoJovem"));
                return jovem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    // Método que lista todos os objetos Jovem no banco de dados
    public List<Jovem> listarTodos() throws SQLException {
        List<Jovem> jovens = new ArrayList<>();
        String sql = "SELECT * FROM jovem";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Jovem jovem = new Jovem();
                jovem.setId(rs.getInt("id"));
                jovem.setNome(rs.getString("nome"));
                jovem.setIdade(rs.getString("idade"));
                jovem.setDadosDoJovem(rs.getString("dadosDoJovem"));
                jovens.add(jovem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return jovens;
    }
}


