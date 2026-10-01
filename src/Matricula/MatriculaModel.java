package Matricula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;

public class MatriculaModel {
    public static void efetivarMatricula(int idAluno, int idPlano, Connection con) throws SQLException {
        String sqlPlano = "SELECT duracao FROM planos WHERE id_plano = ?";
        PreparedStatement stPlano = con.prepareStatement(sqlPlano);
        stPlano.setInt(1, idPlano);
        ResultSet rs = stPlano.executeQuery();

        if (!rs.next()) {
            stPlano.close();
            throw new SQLException("Plano selecionado não existe.");
        }
        int diasDuracao = rs.getInt("duracao");
        rs.close();
        stPlano.close();
        LocalDate hoje = LocalDate.now();
        LocalDate vencimento = hoje.plusDays(diasDuracao);


        String sqlInsert = "INSERT INTO matricula (id_aluno, id_plano, data_inicio, data_vencimento, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stInsert = con.prepareStatement(sqlInsert);
        stInsert.setInt(1, idAluno);
        stInsert.setInt(2, idPlano);
        stInsert.setDate(3, Date.valueOf(hoje));
        stInsert.setDate(4, Date.valueOf(vencimento));
        stInsert.setString(5, "Ativa");
        stInsert.execute();
        stInsert.close();
    }

    public static HashSet<MatriculaBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_matricula, id_aluno, id_plano, data_inicio, data_vencimento, status FROM matricula ORDER BY id_matricula";
        ResultSet rs = st.executeQuery(sql);
        HashSet<MatriculaBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new MatriculaBean(
                rs.getInt("id_matricula"),
                rs.getInt("id_aluno"),
                rs.getInt("id_plano"),
                rs.getDate("data_inicio"),
                rs.getDate("data_vencimento"),
                rs.getString("status")
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void cancelarMatricula(int idMatricula, Connection con) throws SQLException {
        String sql = "UPDATE matricula SET status = 'Cancelada' WHERE id_matricula = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idMatricula);
        st.execute();
        st.close();
    }
}