package Exercicios;

import Equipamentos.EquipamentosController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ExerciciosController {

    public void createExercicio(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Cadastro de Novo Exercício ---");
        System.out.print("Nome do Exercício: ");
        String nome = input.nextLine();
        System.out.print("Grupo Muscular (ex: Peitoral, Dorsal, Quadríceps): ");
        String grupo = input.nextLine();

        new EquipamentosController().listarEquipamentos(con);
        System.out.print("Informe o ID do Equipamento (ou 0 / vazio para Peso Corporal): ");
        String entradaEq = input.nextLine();
        Integer idEquipamento = null;
        if (!entradaEq.trim().isEmpty() && !entradaEq.trim().equals("0")) {
            idEquipamento = Integer.parseInt(entradaEq);
        }

        ExerciciosBean eb = new ExerciciosBean(nome, grupo, idEquipamento);
        ExerciciosModel.create(eb, con);
        System.out.println("Exercício cadastrado com sucesso!");
    }

    public void listarExercicios(Connection con) throws SQLException {
        HashSet<ExerciciosBean> all = ExerciciosModel.listAll(con);
        System.out.println("\n--- Catálogo de Exercícios ---");
        Iterator<ExerciciosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void alterarExercicio(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarExercicios(con);
        System.out.print("\nInforme o ID do exercício a alterar: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Novo Nome: ");
        String nome = input.nextLine();
        System.out.print("Novo Grupo Muscular: ");
        String grupo = input.nextLine();

        new EquipamentosController().listarEquipamentos(con);
        System.out.print("Novo ID do Equipamento (ou 0 para Peso Corporal): ");
        String entradaEq = input.nextLine();
        Integer idEquipamento = null;
        if (!entradaEq.trim().isEmpty() && !entradaEq.trim().equals("0")) {
            idEquipamento = Integer.parseInt(entradaEq);
        }

        ExerciciosBean eb = new ExerciciosBean(id, nome, grupo, idEquipamento);
        ExerciciosModel.update(eb, con);
        System.out.println("Exercício atualizado com sucesso!");
    }

    public void removerExercicio(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarExercicios(con);
        System.out.print("\nInforme o ID do exercício a remover: ");
        int id = Integer.parseInt(input.nextLine());
        ExerciciosModel.remove(id, con);
        System.out.println("Exercício removido com sucesso!");
    }
}