package Matricula;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import Alunos.AlunosController;

public class MatriculaController {

    public void matricularAluno(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nMatrícula de aluno");
        
        new AlunosController().listarAlunos(con);
        System.out.print("\nInforme o ID do Aluno: ");
        int idAluno = Integer.parseInt(input.nextLine());

        System.out.print("Informe o ID do Plano Desejado: ");
        int idPlano = Integer.parseInt(input.nextLine());

        MatriculaModel.efetivarMatricula(idAluno, idPlano, con);
        System.out.println("Matrícula efetivada com sucesso.");
    }

    public void listarMatriculas(Connection con) throws SQLException {
        HashSet<MatriculaBean> all = MatriculaModel.listAll(con);
        System.out.println("\n--- Histórico de Contratos / Matrículas ---");
        Iterator<MatriculaBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void cancelarMatricula(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarMatriculas(con);
        System.out.print("\nInforme o ID da matrícula a ser cancelada: ");
        int idMatricula = Integer.parseInt(input.nextLine());
        MatriculaModel.cancelarMatricula(idMatricula, con);
        System.out.println("Matrícula " + idMatricula + " alterada para Cancelada.");
    }
}