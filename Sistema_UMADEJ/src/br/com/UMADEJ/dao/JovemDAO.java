package br.com.UMADEJ.dao;

import br.com.UMADEJ.factory.ConnectionFactory;
import br.com.UMADEJ.model.Jovem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JovemDAO {
    // Método que salva um objeto Jovem no banco de dados
    public void save(Jovem jovem) {
        String sql = "INSERT INTO jovem (nome, dataCadastro, dataNascimento, dadosDoJovem) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, jovem.getNome());
            pstm.setDate(2, new Date(jovem.getDataCadastro().getTime()));
            pstm.setString(3, jovem.getDataNascimento());
            pstm.setString(4, jovem.getDadosDoJovem());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método que atualiza um objeto Jovem no banco de dados
    public void update(Jovem jovem) {
        String sql = "UPDATE jovem SET nome = ?, dataCadastro = ?, dataNascimento = ?, dadosDoJovem = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, jovem.getNome());
            pstm.setDate(2, new Date(jovem.getDataCadastro().getTime()));
            pstm.setString(3, jovem.getDataNascimento());
            pstm.setString(4, jovem.getDadosDoJovem());
            pstm.setInt(5, jovem.getId());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método que deleta um objeto Jovem do banco de dados pelo ID
    public void deleteByID(int id) {
        String sql = "DELETE FROM jovem WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método que retorna uma lista de todos os objetos Jovem do banco de dados
    public List<Jovem> getJovens() {
        String sql = "SELECT * FROM jovem";

        List<Jovem> jovens = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Jovem jovem = new Jovem();

                jovem.setId(rset.getInt("id"));
                jovem.setNome(rset.getString("nome"));
                jovem.setDataCadastro(rset.getDate("dataCadastro"));
                jovem.setDataNascimento(rset.getString("dataNascimento"));
                jovem.setDadosDoJovem(rset.getString("dadosDoJovem"));

                jovens.add(jovem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jovens;
    }

    // Método que retorna um objeto Jovem específico pelo ID
    public Jovem getJovemByID(int id) throws Exception {
        String sql = "SELECT * FROM jovem WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {
                Jovem jovem = new Jovem();

                jovem.setId(rs.getInt("id"));
                jovem.setNome(rs.getString("nome"));
                jovem.setDataCadastro(rs.getDate("dataCadastro"));
                jovem.setDataNascimento(rs.getString("dataNascimento"));
                jovem.setDadosDoJovem(rs.getString("dadosDoJovem"));

                return jovem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
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
}
