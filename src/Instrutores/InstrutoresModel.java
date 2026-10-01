package Instrutores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class InstrutoresModel {

    public static void create(InstrutoresBean i, Connection con) throws SQLException {
        String sql = "INSERT INTO instrutores (cpf, cref, nome, telefone, email, endereco, especialidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, i.getCpf());
        st.setString(2, i.getCref());
        st.setString(3, i.getNome());
        st.setString(4, i.getTelefone());
        st.setString(5, i.getEmail());
        st.setString(6, i.getEndereco());
        st.setString(7, i.getEspecialidade());
        st.execute();
        st.close();
    }

    public static HashSet<InstrutoresBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_instrutor, cpf, cref, nome, telefone, email, endereco, especialidade FROM instrutores ORDER BY id_instrutor";
        ResultSet rs = st.executeQuery(sql);
        HashSet<InstrutoresBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new InstrutoresBean(
                rs.getInt("id_instrutor"),
                rs.getString("cpf"),
                rs.getString("cref"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco"),
                rs.getString("especialidade")
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void update(InstrutoresBean i, Connection con) throws SQLException {
        String sql = "UPDATE instrutores SET cpf=?, cref=?, nome=?, telefone=?, email=?, endereco=?, especialidade=? WHERE id_instrutor=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, i.getCpf());
        st.setString(2, i.getCref());
        st.setString(3, i.getNome());
        st.setString(4, i.getTelefone());
        st.setString(5, i.getEmail());
        st.setString(6, i.getEndereco());
        st.setString(7, i.getEspecialidade());
        st.setInt(8, i.getIdInstrutor());
        st.execute();
        st.close();
    }

    public static void remove(int idInstrutor, Connection con) throws SQLException {
        String sql = "DELETE FROM instrutores WHERE id_instrutor=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idInstrutor);
        st.execute();
        st.close();
    }
}