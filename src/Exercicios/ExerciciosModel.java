package Exercicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;

public class ExerciciosModel {

    public static void create(ExerciciosBean e, Connection con) throws SQLException {
        String sql = "INSERT INTO exercicios (nome_exercicio, grupo_muscular, id_equipamento) VALUES (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, e.getNomeExercicio());
        st.setString(2, e.getGrupoMuscular());
        if (e.getIdEquipamento() == null || e.getIdEquipamento() <= 0) {
            st.setNull(3, Types.INTEGER);
        } else {
            st.setInt(3, e.getIdEquipamento());
        }
        st.execute();
        st.close();
    }

    public static HashSet<ExerciciosBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_exercicio, nome_exercicio, grupo_muscular, id_equipamento FROM exercicios ORDER BY id_exercicio";
        ResultSet rs = st.executeQuery(sql);
        HashSet<ExerciciosBean> list = new HashSet<>();
        while (rs.next()) {
            int idEq = rs.getInt("id_equipamento");
            Integer idEquipamento = rs.wasNull() ? null : idEq;
            list.add(new ExerciciosBean(
                rs.getInt("id_exercicio"),
                rs.getString("nome_exercicio"),
                rs.getString("grupo_muscular"),
                idEquipamento
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void update(ExerciciosBean e, Connection con) throws SQLException {
        String sql = "UPDATE exercicios SET nome_exercicio=?, grupo_muscular=?, id_equipamento=? WHERE id_exercicio=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, e.getNomeExercicio());
        st.setString(2, e.getGrupoMuscular());
        if (e.getIdEquipamento() == null || e.getIdEquipamento() <= 0) {
            st.setNull(3, Types.INTEGER);
        } else {
            st.setInt(3, e.getIdEquipamento());
        }
        st.setInt(4, e.getIdExercicio());
        st.execute();
        st.close();
    }

    public static void remove(int idExercicio, Connection con) throws SQLException {
        String sql = "DELETE FROM exercicios WHERE id_exercicio=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idExercicio);
        st.execute();
        st.close();
    }
}