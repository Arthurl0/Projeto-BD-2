package Planos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class PlanosModel {

    public static void create(PlanosBean p, Connection con) throws SQLException {
        String sql = "INSERT INTO planos (preco, descricao, nome_plano, duracao) VALUES (?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setDouble(1, p.getPreco());
        st.setString(2, p.getDescricao());
        st.setString(3, p.getNomePlano());
        st.setInt(4, p.getDuracao());
        st.execute();
        st.close();
    }

    public static HashSet<PlanosBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_plano, preco, descricao, nome_plano, duracao FROM planos ORDER BY id_plano";
        ResultSet rs = st.executeQuery(sql);
        HashSet<PlanosBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new PlanosBean(
                rs.getInt("id_plano"),
                rs.getDouble("preco"),
                rs.getString("descricao"),
                rs.getString("nome_plano"),
                rs.getInt("duracao")
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void update(PlanosBean p, Connection con) throws SQLException {
        String sql = "UPDATE planos SET preco=?, descricao=?, nome_plano=?, duracao=? WHERE id_plano=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setDouble(1, p.getPreco());
        st.setString(2, p.getDescricao());
        st.setString(3, p.getNomePlano());
        st.setInt(4, p.getDuracao());
        st.setInt(5, p.getIdPlano());
        st.execute();
        st.close();
    }

    public static void remove(int idPlano, Connection con) throws SQLException {
        String sql = "DELETE FROM planos WHERE id_plano=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idPlano);
        st.execute();
        st.close();
    }
}