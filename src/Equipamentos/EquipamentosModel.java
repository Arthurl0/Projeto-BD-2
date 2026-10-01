package Equipamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class EquipamentosModel {

    public static void create(EquipamentosBean e, Connection con) throws SQLException {
        String sql = "INSERT INTO equipamentos (nome, marca, status) VALUES (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, e.getNome());
        st.setString(2, e.getMarca());
        st.setString(3, e.getStatus());
        st.execute();
        st.close();
    }

    public static HashSet<EquipamentosBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_equipamento, nome, marca, status FROM equipamentos ORDER BY id_equipamento";
        ResultSet rs = st.executeQuery(sql);
        HashSet<EquipamentosBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new EquipamentosBean(
                rs.getInt("id_equipamento"),
                rs.getString("nome"),
                rs.getString("marca"),
                rs.getString("status")
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void update(EquipamentosBean e, Connection con) throws SQLException {
        String sql = "UPDATE equipamentos SET nome=?, marca=?, status=? WHERE id_equipamento=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, e.getNome());
        st.setString(2, e.getMarca());
        st.setString(3, e.getStatus());
        st.setInt(4, e.getIdEquipamento());
        st.execute();
        st.close();
    }

    public static void remove(int idEquipamento, Connection con) throws SQLException {
        String sql = "DELETE FROM equipamentos WHERE id_equipamento=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idEquipamento);
        st.execute();
        st.close();
    }
}