package Relatorios;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Alunos.AlunosController;

public class RelatoriosController {

    public void executarRelatorioFicha(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        new AlunosController().listarAlunos(con);
        System.out.print("\nInforme o ID do Aluno para gerar a Ficha de Treino: ");
        int idAluno = Integer.parseInt(input.nextLine());
        RelatoriosModel.relatorioFichaAluno(idAluno, con);
    }

    public void executarRelatorioFaturamento(Connection con) throws SQLException {
        RelatoriosModel.relatorioFaturamentoPlanos(con);
    }

    public void executarRelatorioEvolucao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        new AlunosController().listarAlunos(con);
        System.out.print("\nInforme o ID do Aluno para consultar o Histórico: ");
        int idAluno = Integer.parseInt(input.nextLine());
        RelatoriosModel.relatorioEvolucaoAluno(idAluno, con);
    }
}