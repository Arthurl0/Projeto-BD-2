package Relatorios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelatoriosModel {
    public static void relatorioFichaAluno(int idAluno, Connection con) throws SQLException {
        String sql = "SELECT a.nome AS aluno, i.nome AS instrutor, f.objetivo, it.divisao_treino, " +
                     "e.nome_exercicio, COALESCE(eq.nome, 'Sem Equipamento (Peso Corporal)') AS equipamento, " +
                     "it.series, it.repeticoes, COALESCE(it.carga, 0) AS carga " +
                     "FROM fichas f " +
                     "JOIN alunos a ON f.id_aluno = a.id_aluno " +
                     "JOIN instrutores i ON f.id_instrutor = i.id_instrutor " +
                     "JOIN item_treino it ON it.id_ficha = f.id_ficha " +
                     "JOIN exercicios e ON it.id_exercicio = e.id_exercicio " +
                     "LEFT JOIN equipamentos eq ON e.id_equipamento = eq.id_equipamento " +
                     "WHERE a.id_aluno = ? ORDER BY it.divisao_treino, e.nome_exercicio";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idAluno);
        ResultSet rs = st.executeQuery();

        System.out.println("Ficha de treino");
        boolean encontrou = false;
        while (rs.next()) {
            if (!encontrou) {
                System.out.println("Aluno: " + rs.getString("aluno") + " | Instrutor: " + rs.getString("instrutor"));
                System.out.println("Objetivo: " + rs.getString("objetivo"));
                System.out.printf("%-20s %-25s %-25s %-8s %-8s %-8s\n", "Divisão", "Exercício", "Equipamento", "Séries", "Reps", "Carga(kg)");
                encontrou = true;
            }
            System.out.printf("%-20s %-25s %-25s %-8d %-8d %-8.2f\n",
                rs.getString("divisao_treino"),
                rs.getString("nome_exercicio"),
                rs.getString("equipamento"),
                rs.getInt("series"),
                rs.getInt("repeticoes"),
                rs.getDouble("carga"));
        }
        if (!encontrou) {
            System.out.println("Nenhum exercício cadastrado para o aluno ID " + idAluno);
        }
        rs.close();
        st.close();
    }

    // Faturamento e Volume de Alunos por Plano consultado
    public static void relatorioFaturamentoPlanos(Connection con) throws SQLException {
        String sql = "SELECT p.nome_plano, p.preco, " +
                     "COUNT(m.id_matricula) AS total_ativas, " +
                     "COALESCE(SUM(p.preco), 0) AS receita_total " +
                     "FROM planos p " +
                     "LEFT JOIN matricula m ON p.id_plano = m.id_plano AND m.status = 'Ativa' " +
                     "GROUP BY p.id_plano, p.nome_plano, p.preco " +
                     "ORDER BY receita_total DESC";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("Relatório de contratos ativos e receitas");
        System.out.printf("%-25s %-15s %-18s %-15s\n", "Plano", "Valor Unit.", "Matrículas Ativas", "Receita Gerada");
        System.out.println("-----------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-25s R$ %-12.2f %-18d R$ %-12.2f\n",
                rs.getString("nome_plano"),
                rs.getDouble("preco"),
                rs.getInt("total_ativas"),
                rs.getDouble("receita_total"));
        }
        rs.close();
        st.close();
    }

    public static void relatorioEvolucaoAluno(int idAluno, Connection con) throws SQLException {
        String sql = "SELECT a.nome AS aluno, av.data_avaliacao, i.nome AS instrutor, " +
                     "av.peso, av.altura, ROUND(av.peso / (av.altura * av.altura), 2) AS imc, " +
                     "COALESCE(av.percentual_gordura, 0) AS gordura, av.observacoes " +
                     "FROM avaliacao_fisica av " +
                     "JOIN alunos a ON av.id_aluno = a.id_aluno " +
                     "JOIN instrutores i ON av.id_instrutor = i.id_instrutor " +
                     "WHERE a.id_aluno = ? ORDER BY av.data_avaliacao ASC";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idAluno);
        ResultSet rs = st.executeQuery();

        System.out.println("Histórico de avaliações e IMC");
        boolean existe_av = false;
        while (rs.next()) {
            if (!existe_av) {
                System.out.println("Aluno: " + rs.getString("aluno"));
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("%-12s %-20s %-10s %-10s %-8s %-10s %-25s\n", "Data", "Avaliador", "Peso(kg)", "Alt(m)", "IMC", "% Gordura", "Obs");
                existe_av = true;
            }
            System.out.printf("%-12s %-20s %-10.2f %-10.2f %-8.2f %-10.2f %-25s\n",
                rs.getDate("data_avaliacao"),
                rs.getString("instrutor"),
                rs.getDouble("peso"),
                rs.getDouble("altura"),
                rs.getDouble("imc"),
                rs.getDouble("gordura"),
                rs.getString("observacoes"));
        }
        if (!existe_av) {
            System.out.println("Não existe um histórico de avaliação física localizado para o aluno");
        }
        rs.close();
        st.close();
    }
}