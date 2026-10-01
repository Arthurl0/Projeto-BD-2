package Planos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PlanosController {

    public void createPlano(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Cadastro de Novo Plano ---");
        System.out.print("Nome do Plano: ");
        String nome = input.nextLine();
        System.out.print("Descrição: ");
        String descricao = input.nextLine();
        System.out.print("Preço (ex: 129.90): ");
        double preco = Double.parseDouble(input.nextLine().replace(",", "."));
        System.out.print("Duração (em dias): ");
        int duracao = Integer.parseInt(input.nextLine());

        PlanosBean pb = new PlanosBean(preco, descricao, nome, duracao);
        PlanosModel.create(pb, con);
        System.out.println("Plano cadastrado com sucesso!");
    }

    public void listarPlanos(Connection con) throws SQLException {
        HashSet<PlanosBean> all = PlanosModel.listAll(con);
        System.out.println("\n--- Lista de Planos de Adesão ---");
        Iterator<PlanosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void alterarPlano(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarPlanos(con);
        System.out.print("\nInforme o ID do plano a ser alterado: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Nova Descrição: ");
        String descricao = input.nextLine();
        System.out.print("Novo Preço: ");
        double preco = Double.parseDouble(input.nextLine().replace(",", "."));
        System.out.print("Nova Duração (dias): ");
        int duracao = Integer.parseInt(input.nextLine());

        PlanosBean pb = new PlanosBean(id, preco, descricao, nome, duracao);
        PlanosModel.update(pb, con);
        System.out.println("Plano atualizado com sucesso!");
    }

    public void removerPlano(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarPlanos(con);
        System.out.print("\nInforme o ID do plano a remover: ");
        int id = Integer.parseInt(input.nextLine());
        PlanosModel.remove(id, con);
        System.out.println("Plano removido com sucesso!");
    }
}