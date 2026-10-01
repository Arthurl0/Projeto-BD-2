package Equipamentos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class EquipamentosController {

    public void createEquipamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Cadastro de Novo Equipamento ---");
        System.out.print("Nome do Equipamento: ");
        String nome = input.nextLine();
        System.out.print("Marca / Fabricante: ");
        String marca = input.nextLine();
        System.out.print("Status (ex: Disponível, Em Manutenção, Interditado): ");
        String status = input.nextLine();

        EquipamentosBean eb = new EquipamentosBean(nome, marca, status);
        EquipamentosModel.create(eb, con);
        System.out.println("Equipamento cadastrado com sucesso!");
    }

    public void listarEquipamentos(Connection con) throws SQLException {
        HashSet<EquipamentosBean> all = EquipamentosModel.listAll(con);
        System.out.println("\n--- Parque de Equipamentos ---");
        Iterator<EquipamentosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void alterarEquipamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEquipamentos(con);
        System.out.print("\nInforme o ID do equipamento a ser alterado: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Nova Marca: ");
        String marca = input.nextLine();
        System.out.print("Novo Status: ");
        String status = input.nextLine();

        EquipamentosBean eb = new EquipamentosBean(id, nome, marca, status);
        EquipamentosModel.update(eb, con);
        System.out.println("Equipamento atualizado com sucesso!");
    }

    public void removerEquipamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEquipamentos(con);
        System.out.print("\nInforme o ID do equipamento a remover: ");
        int id = Integer.parseInt(input.nextLine());
        EquipamentosModel.remove(id, con);
        System.out.println("Equipamento removido com sucesso!");
    }
}