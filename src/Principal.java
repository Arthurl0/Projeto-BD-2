import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Alunos.AlunosController;
import Instrutores.InstrutoresController;
import Planos.PlanosController;
import Equipamentos.EquipamentosController;
import Exercicios.ExerciciosController;
import Matricula.MatriculaController;
import AvaliacaoFisica.AvaliacaoFisicaController;
import Fichas.FichasController;
import Relatorios.RelatoriosController;

public class Principal {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection conexao = c.getConnection();
        int opPrincipal = 0;

        do {
            limpar_terminal();
            System.out.println("========================================================");
            System.out.println("       SISTEMA DE GESTÃO DE ACADEMIA E TREINOS          ");
            System.out.println("========================================================");
            System.out.println(" 1 - Gestão de Cadastros (CRUDs de Entidades)");
            System.out.println(" 2 - Processos de Negócio (Tabelas Associativas)");
            System.out.println(" 3 - Relatórios Analíticos");
            System.out.println(" 0 - Sair do Sistema");
            System.out.println("--------------------------------------------------------");
            System.out.print("Escolha o módulo: ");
            opPrincipal = lerInteiro();

            switch (opPrincipal) {
                case 1: submenuCadastros(conexao); break;
                case 2: submenuNegocio(conexao); break;
                case 3: submenuRelatorios(conexao); break;
                case 0: System.out.println("\nEncerrando aplicação..."); break;
                default: System.out.println("\nOpção inválida!"); pausar(); break;
            }

        } while (opPrincipal != 0);

        c.closeConnection();
    }

    private static void submenuCadastros(Connection con) {
        int op = 0;
        do {
            limpar_terminal();
            System.out.println("--- [MÓDULO: GESTÃO DE CADASTROS (CRUDS)] ---");
            System.out.println(" 1 - Alunos");
            System.out.println(" 2 - Instrutores");
            System.out.println(" 3 - Planos de Adesão");
            System.out.println(" 4 - Equipamentos");
            System.out.println(" 5 - Catálogo de Exercícios");
            System.out.println(" 0 - Voltar ao Menu Principal");
            System.out.println("---------------------------------------------");
            System.out.print("Opção: ");
            op = lerInteiro();

            try {
                switch (op) {
                    case 1: menuCrudGenerico("Alunos", con); break;
                    case 2: menuCrudGenerico("Instrutores", con); break;
                    case 3: menuCrudGenerico("Planos", con); break;
                    case 4: menuCrudGenerico("Equipamentos", con); break;
                    case 5: menuCrudGenerico("Exercicios", con); break;
                    case 0: break;
                    default: System.out.println("Opção inválida!"); pausar(); break;
                }
            } catch (SQLException erro) {
                System.out.println("\n[ERRO DE BANCO]: " + erro.getMessage());
                pausar();
            }
        } while (op != 0);
    }

    private static void menuCrudGenerico(String entidade, Connection con) throws SQLException {
        int acao = 0;
        do {
            limpar_terminal();
            System.out.println("--- [CRUD: " + entidade.toUpperCase() + "] ---");
            System.out.println(" 1 - Cadastrar " + entidade);
            System.out.println(" 2 - Listar " + entidade);
            System.out.println(" 3 - Alterar " + entidade);
            System.out.println(" 4 - Remover " + entidade);
            System.out.println(" 0 - Voltar");
            System.out.println("---------------------------------------------");
            System.out.print("Ação: ");
            acao = lerInteiro();

            if (acao == 0) break;

            switch (entidade) {
                case "Alunos":
                    AlunosController ac = new AlunosController();
                    if (acao == 1) ac.createAluno(con);
                    else if (acao == 2) ac.listarAlunos(con);
                    else if (acao == 3) ac.alterarAluno(con);
                    else if (acao == 4) ac.removerAluno(con);
                    break;
                case "Instrutores":
                    InstrutoresController ic = new InstrutoresController();
                    if (acao == 1) ic.createInstrutor(con);
                    else if (acao == 2) ic.listarInstrutores(con);
                    else if (acao == 3) ic.alterarInstrutor(con);
                    else if (acao == 4) ic.removerInstrutor(con);
                    break;
                case "Planos":
                    PlanosController pc = new PlanosController();
                    if (acao == 1) pc.createPlano(con);
                    else if (acao == 2) pc.listarPlanos(con);
                    else if (acao == 3) pc.alterarPlano(con);
                    else if (acao == 4) pc.removerPlano(con);
                    break;
                case "Equipamentos":
                    EquipamentosController ec = new EquipamentosController();
                    if (acao == 1) ec.createEquipamento(con);
                    else if (acao == 2) ec.listarEquipamentos(con);
                    else if (acao == 3) ec.alterarEquipamento(con);
                    else if (acao == 4) ec.removerEquipamento(con);
                    break;
                case "Exercicios":
                    ExerciciosController exc = new ExerciciosController();
                    if (acao == 1) exc.createExercicio(con);
                    else if (acao == 2) exc.listarExercicios(con);
                    else if (acao == 3) exc.alterarExercicio(con);
                    else if (acao == 4) exc.removerExercicio(con);
                    break;
            }
            pausar();
        } while (acao != 0);
    }

    private static void submenuNegocio(Connection con) {
        int op = 0;
        do {
            limpar_terminal();
            System.out.println("--- [MÓDULO: PROCESSOS DE NEGÓCIO (ASSOCIATIVAS)] ---");
            System.out.println(" 1 - Efetivar Nova Matrícula em Plano");
            System.out.println(" 2 - Listar Contratos de Matrícula");
            System.out.println(" 3 - Cancelar Contrato de Matrícula");
            System.out.println(" 4 - Registrar Avaliação Física (Aluno + Instrutor)");
            System.out.println(" 5 - Listar Avaliações Físicas");
            System.out.println(" 6 - Prescrever Ficha de Treino com Itens (Transacional)");
            System.out.println(" 7 - Listar Fichas de Treino");
            System.out.println(" 0 - Voltar ao Menu Principal");
            System.out.println("-----------------------------------------------------");
            System.out.print("Opção: ");
            op = lerInteiro();

            try {
                switch (op) {
                    case 1: new MatriculaController().matricularAluno(con); pausar(); break;
                    case 2: new MatriculaController().listarMatriculas(con); pausar(); break;
                    case 3: new MatriculaController().cancelarMatricula(con); pausar(); break;
                    case 4: new AvaliacaoFisicaController().registrarAvaliacao(con); pausar(); break;
                    case 5: new AvaliacaoFisicaController().listarAvaliacoes(con); pausar(); break;
                    case 6: new FichasController().prescreverFicha(con); pausar(); break;
                    case 7: new FichasController().listarFichas(con); pausar(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida!"); pausar(); break;
                }
            } catch (SQLException erro) {
                System.out.println("\n[ERRO DE BANCO]: " + erro.getMessage());
                pausar();
            }
        } while (op != 0);
    }

    private static void submenuRelatorios(Connection con) {
        int op = 0;
        do {
            limpar_terminal();
            System.out.println("--- [MÓDULO: RELATÓRIOS ANALÍTICOS] ---");
            System.out.println(" 1 - Ficha Completa de Treino do Aluno");
            System.out.println(" 2 - Faturamento e Adesão por Plano");
            System.out.println(" 3 - Evolução Antropométrica e IMC");
            System.out.println(" 0 - Voltar ao Menu Principal");
            System.out.println("---------------------------------------");
            System.out.print("Opção: ");
            op = lerInteiro();

            try {
                RelatoriosController rc = new RelatoriosController();
                switch (op) {
                    case 1: rc.executarRelatorioFicha(con); pausar(); break;
                    case 2: rc.executarRelatorioFaturamento(con); pausar(); break;
                    case 3: rc.executarRelatorioEvolucao(con); pausar(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida!"); pausar(); break;
                }
            } catch (SQLException erro) {
                System.out.println("\n[ERRO DE BANCO]: " + erro.getMessage());
                pausar();
            }
        } while (op != 0);
    }

    private static void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        input.nextLine();
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void limpar_terminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }
}