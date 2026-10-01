package Alunos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class AlunosController {
    public void createAluno(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Cadastro de Novo Aluno ---");
        System.out.print("Nome completo: ");
        String nome = input.nextLine();
        System.out.print("Telefone (com DDD): ");
        String telefone = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        Date dataNasc = Date.valueOf(input.nextLine());
        System.out.print("E-mail: ");
        String email = input.nextLine();
        System.out.print("CPF (11 dígitos): ");
        String cpf = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();

        AlunosBean ab = new AlunosBean(nome, telefone, dataNasc, email, cpf, endereco);
        AlunosModel.create(ab, con);
        System.out.println("Aluno cadastrado com sucesso!");
    }
    public void listarAlunos(Connection con) throws SQLException {
        HashSet<AlunosBean> all = AlunosModel.listAll(con);
        System.out.println("\n--- Lista de Alunos ---");
        Iterator<AlunosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void alterarAluno(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAlunos(con);
        System.out.print("\nInforme o ID do aluno a ser alterado: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = input.nextLine();
        System.out.print("Nova Data Nasc (AAAA-MM-DD): ");
        Date dataNasc = Date.valueOf(input.nextLine());
        System.out.print("Novo E-mail: ");
        String email = input.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = input.nextLine();
        System.out.print("Novo Endereço: ");
        String endereco = input.nextLine();

        AlunosBean ab = new AlunosBean(id, nome, telefone, dataNasc, email, cpf, endereco);
        AlunosModel.update(ab, con);
        System.out.println("Informações alteradas");
    }
    public void removerAluno(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAlunos(con);
        System.out.print("\nInforme o ID do aluno a remover: ");
        int id = Integer.parseInt(input.nextLine());
        AlunosModel.remove(id, con);
        System.out.println("Aluno excluído");
        
    }
}