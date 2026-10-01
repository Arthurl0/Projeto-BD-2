package Fichas;

import Alunos.AlunosController;
import Exercicios.ExerciciosController;
import Instrutores.InstrutoresController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FichasController {

    public void prescreverFicha(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Processo de Negócio: Prescrição Completa de Treino ---");

        new AlunosController().listarAlunos(con);
        System.out.print("\nInforme o ID do Aluno: ");
        int idAluno = Integer.parseInt(input.nextLine());

        new InstrutoresController().listarInstrutores(con);
        System.out.print("\nInforme o ID do Instrutor Responsável: ");
        int idInstrutor = Integer.parseInt(input.nextLine());

        System.out.print("Objetivo do treino (ex: Hipertrofia ABC, Resistência): ");
        String objetivo = input.nextLine();

        System.out.print("Validade do treino em dias (ex: 90): ");
        int validadeDias = Integer.parseInt(input.nextLine());

        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataInicio.plusDays(validadeDias);

        FichasBean ficha = new FichasBean(idAluno, idInstrutor, Date.valueOf(dataInicio), Date.valueOf(dataFim), objetivo);
        List<ItemTreinoBean> itens = new ArrayList<>();

        System.out.println("\n--- Adição de Exercícios na Ficha ---");
        ExerciciosController ec = new ExerciciosController();
        ec.listarExercicios(con);

        int adicionarMais = 1;
        do {
            System.out.print("\nInforme o ID do Exercício a incluir: ");
            int idExercicio = Integer.parseInt(input.nextLine());

            System.out.print("Divisão do Treino (ex: Treino A, Treino B, Treino C): ");
            String divisao = input.nextLine();

            System.out.print("Quantidade de séries: ");
            int series = Integer.parseInt(input.nextLine());

            System.out.print("Quantidade de repetições: ");
            int repeticoes = Integer.parseInt(input.nextLine());

            System.out.print("Carga em kg [opcional / Enter para Peso Corporal]: ");
            String cargaStr = input.nextLine();
            Double carga = null;
            if (!cargaStr.trim().isEmpty()) {
                carga = Double.parseDouble(cargaStr.replace(",", "."));
            }

            itens.add(new ItemTreinoBean(idExercicio, divisao, series, repeticoes, carga));

            System.out.print("\nDeseja adicionar outro exercício a esta ficha? (1 - Sim / 0 - Não): ");
            adicionarMais = Integer.parseInt(input.nextLine());
        } while (adicionarMais == 1);

        FichasModel.criarFichaComItens(ficha, itens, con);
        System.out.println("\nFicha de treino prescrita com " + itens.size() + " exercício(s) vinculados!");
    }

    public void listarFichas(Connection con) throws SQLException {
        HashSet<FichasBean> all = FichasModel.listAll(con);
        System.out.println("\n--- Fichas de Treino Cadastradas ---");
        Iterator<FichasBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}