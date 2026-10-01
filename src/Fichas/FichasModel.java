package Fichas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;
import java.util.List;

public class FichasModel {

    public static void criarFichaComItens(FichasBean ficha, List<ItemTreinoBean> itens, Connection con) throws SQLException {
        boolean autoCommitOriginal = con.getAutoCommit();
        try {
            con.setAutoCommit(false);

            String sqlFicha = "INSERT INTO fichas (data_inicio, data_fim, objetivo, id_aluno, id_instrutor) " +
                              "VALUES (?, ?, ?, ?, ?) RETURNING id_ficha";
            PreparedStatement stFicha = con.prepareStatement(sqlFicha);
            stFicha.setDate(1, ficha.getDataInicio());
            stFicha.setDate(2, ficha.getDataFim());
            stFicha.setString(3, ficha.getObjetivo());
            stFicha.setInt(4, ficha.getIdAluno());
            stFicha.setInt(5, ficha.getIdInstrutor());

            ResultSet rsFicha = stFicha.executeQuery();
            if (!rsFicha.next()) {
                throw new SQLException("Falha ao recuperar o ID gerado para a ficha de treino.");
            }
            int idFichaGerada = rsFicha.getInt(1);
            rsFicha.close();
            stFicha.close();

            String sqlItem = "INSERT INTO item_treino (divisao_treino, series, repeticoes, carga, id_exercicio, id_ficha) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stItem = con.prepareStatement(sqlItem);

            for (ItemTreinoBean item : itens) {
                stItem.setString(1, item.getDivisaoTreino());
                stItem.setInt(2, item.getSeries());
                stItem.setInt(3, item.getRepeticoes());

                if (item.getCarga() != null) {
                    stItem.setDouble(4, item.getCarga());
                } else {
                    stItem.setNull(4, Types.NUMERIC);
                }

                stItem.setInt(5, item.getIdExercicio());
                stItem.setInt(6, idFichaGerada);
                stItem.addBatch();
            }

            stItem.executeBatch();
            stItem.close();

            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(autoCommitOriginal);
        }
    }

    public static HashSet<FichasBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_ficha, id_aluno, id_instrutor, data_inicio, data_fim, objetivo FROM fichas ORDER BY id_ficha";
        ResultSet rs = st.executeQuery(sql);
        HashSet<FichasBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new FichasBean(
                rs.getInt("id_ficha"),
                rs.getInt("id_aluno"),
                rs.getInt("id_instrutor"),
                rs.getDate("data_inicio"),
                rs.getDate("data_fim"),
                rs.getString("objetivo")
            ));
        }
        rs.close();
        st.close();
        return list;
    }
}