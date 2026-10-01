package AvaliacaoFisica;

import Alunos.AlunosController;
import Instrutores.InstrutoresController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AvaliacaoFisicaController {

    public void registrarAvaliacao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Processo de Negócio: Registrar Avaliação Física ---");

        new AlunosController().listarAlunos(con);
        System.out.print("\nInforme o ID do Aluno a ser avaliado: ");
        int idAluno = Integer.parseInt(input.nextLine());

        new InstrutoresController().listarInstrutores(con);
        System.out.print("\nInforme o ID do Instrutor avaliador: ");
        int idInstrutor = Integer.parseInt(input.nextLine());

        System.out.print("Peso do aluno em kg (ex: 78.5): ");
        double peso = Double.parseDouble(input.nextLine().replace(",", "."));

        System.out.print("Altura do aluno em metros (ex: 1.75): ");
        double altura = Double.parseDouble(input.nextLine().replace(",", "."));

        System.out.print("Percentual de gordura corporal (%) [opcional - Enter para pular]: ");
        String gorduraStr = input.nextLine();
        Double percentualGordura = null;
        if (!gorduraStr.trim().isEmpty()) {
            percentualGordura = Double.parseDouble(gorduraStr.replace(",", "."));
        }

        System.out.print("Observações e metas: ");
        String observacoes = input.nextLine();

        double imc = peso / (altura * altura);
        String diagnostico;
        if (imc < 18.5) diagnostico = "Abaixo do peso ideal";
        else if (imc < 25.0) diagnostico = "Peso adequado / Eutrófico";
        else if (imc < 30.0) diagnostico = "Sobrepeso";
        else diagnostico = "Obesidade";

        System.out.printf("\n[Diagnóstico Calculado] IMC: %.2f (%s)\n", imc, diagnostico);

        AvaliacaoFisicaBean av = new AvaliacaoFisicaBean(idAluno, idInstrutor, Date.valueOf(LocalDate.now()), peso, altura, percentualGordura, observacoes);
        AvaliacaoFisicaModel.registrarAvaliacao(av, con);
        System.out.println("Avaliação física gravada com sucesso!");
    }

    public void listarAvaliacoes(Connection con) throws SQLException {
        HashSet<AvaliacaoFisicaBean> all = AvaliacaoFisicaModel.listAll(con);
        System.out.println("\n--- Histórico Geral de Avaliações Físicas ---");
        Iterator<AvaliacaoFisicaBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}