package AvaliacaoFisica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;

public class AvaliacaoFisicaModel {

    public static void registrarAvaliacao(AvaliacaoFisicaBean av, Connection con) throws SQLException {
        String sql = "INSERT INTO avaliacao_fisica (id_aluno, id_instrutor, data_avaliacao, peso, altura, percentual_gordura, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, av.getIdAluno());
        st.setInt(2, av.getIdInstrutor());
        st.setDate(3, av.getDataAvaliacao());
        st.setDouble(4, av.getPeso());
        st.setDouble(5, av.getAltura());

        if (av.getPercentualGordura() != null) {
            st.setDouble(6, av.getPercentualGordura());
        } else {
            st.setNull(6, Types.NUMERIC);
        }

        st.setString(7, av.getObservacoes());
        st.execute();
        st.close();
    }

    public static HashSet<AvaliacaoFisicaBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_avaliacao, id_aluno, id_instrutor, data_avaliacao, peso, altura, percentual_gordura, observacoes " +
                     "FROM avaliacao_fisica ORDER BY data_avaliacao DESC";
        ResultSet rs = st.executeQuery(sql);
        HashSet<AvaliacaoFisicaBean> list = new HashSet<>();
        while (rs.next()) {
            double gorduraVal = rs.getDouble("percentual_gordura");
            Double gordura = rs.wasNull() ? null : gorduraVal;

            list.add(new AvaliacaoFisicaBean(
                rs.getInt("id_avaliacao"),
                rs.getInt("id_aluno"),
                rs.getInt("id_instrutor"),
                rs.getDate("data_avaliacao"),
                rs.getDouble("peso"),
                rs.getDouble("altura"),
                gordura,
                rs.getString("observacoes")
            ));
        }
        rs.close();
        st.close();
        return list;
    }
}