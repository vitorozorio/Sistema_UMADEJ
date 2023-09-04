package br.com.UMADEJ.dao;

import br.com.UMADEJ.factory.ConnectionFactory;
import br.com.UMADEJ.model.Adm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdmDAO {
    // Método que salva um objeto Adm no banco de dados
    public void save(Adm adm) {
        String sql = "INSERT INTO admins(nome) VALUES (?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, adm.getNome());

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

    // Método que atualiza um objeto Adm no banco de dados
    public void update(Adm adm) {
        String sql = "UPDATE admins SET nome = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, adm.getNome());
            pstm.setInt(2, adm.getId());

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

    // Método que deleta um objeto Adm do banco de dados pelo ID
    public void deleteByID(int id) {
        String sql = "DELETE FROM admins WHERE id = ?";

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

    // Método que retorna uma lista de todos os objetos Adm do banco de dados
    public List<Adm> getAdms() {
        String sql = "SELECT * FROM admins";

        List<Adm> adms = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Adm adm = new Adm();

                adm.setId(rset.getInt("id"));
                adm.setNome(rset.getString("nome"));

                adms.add(adm);
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

        return adms;
    }

    // Método que retorna um objeto Adm específico pelo ID
    public Adm getAdmByID(int id) throws Exception {
        String sql = "SELECT * FROM admins WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            if (rs.next()) {
                Adm adm = new Adm();

                adm.setId(rs.getInt("id"));
                adm.setNome(rs.getString("nome"));

                return adm;
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