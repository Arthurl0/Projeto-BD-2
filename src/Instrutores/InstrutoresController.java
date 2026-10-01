package Instrutores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class InstrutoresController {

    public void createInstrutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Cadastro de Novo Instrutor ---");
        System.out.print("Nome completo: ");
        String nome = input.nextLine();
        System.out.print("CPF (11 dígitos): ");
        String cpf = input.nextLine();
        System.out.print("CREF: ");
        String cref = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("E-mail: ");
        String email = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Especialidade (ex: Musculação, Funcional): ");
        String especialidade = input.nextLine();

        InstrutoresBean ib = new InstrutoresBean(cpf, cref, nome, telefone, email, endereco, especialidade);
        InstrutoresModel.create(ib, con);
        System.out.println("Instrutor cadastrado com sucesso!");
    }

    public void listarInstrutores(Connection con) throws SQLException {
        HashSet<InstrutoresBean> all = InstrutoresModel.listAll(con);
        System.out.println("\n--- Lista de Instrutores ---");
        Iterator<InstrutoresBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void alterarInstrutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarInstrutores(con);
        System.out.print("\nInforme o ID do instrutor a ser alterado: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = input.nextLine();
        System.out.print("Novo CREF: ");
        String cref = input.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = input.nextLine();
        System.out.print("Novo E-mail: ");
        String email = input.nextLine();
        System.out.print("Novo Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Nova Especialidade: ");
        String especialidade = input.nextLine();

        InstrutoresBean ib = new InstrutoresBean(id, cpf, cref, nome, telefone, email, endereco, especialidade);
        InstrutoresModel.update(ib, con);
        System.out.println("Instrutor atualizado com sucesso!");
    }

    public void removerInstrutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarInstrutores(con);
        System.out.print("\nInforme o ID do instrutor a remover: ");
        int id = Integer.parseInt(input.nextLine());
        InstrutoresModel.remove(id, con);
        System.out.println("Instrutor removido com sucesso!");
    }
}