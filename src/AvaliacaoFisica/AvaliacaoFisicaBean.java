package AvaliacaoFisica;

import java.sql.Date;

public class AvaliacaoFisicaBean {
    private int idAvaliacao;
    private int idAluno;
    private int idInstrutor;
    private Date dataAvaliacao;
    private double peso;
    private double altura;
    private Double percentualGordura;
    private String observacoes;

    public AvaliacaoFisicaBean(int idAvaliacao, int idAluno, int idInstrutor, Date dataAvaliacao, double peso, double altura, Double percentualGordura, String observacoes) {
        this.idAvaliacao = idAvaliacao;
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.altura = altura;
        this.percentualGordura = percentualGordura;
        this.observacoes = observacoes;
    }

    public AvaliacaoFisicaBean(int idAluno, int idInstrutor, Date dataAvaliacao, double peso, double altura, Double percentualGordura, String observacoes) {
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.altura = altura;
        this.percentualGordura = percentualGordura;
        this.observacoes = observacoes;
    }

    public int getIdAvaliacao() { return idAvaliacao; }
    public int getIdAluno() { return idAluno; }
    public int getIdInstrutor() { return idInstrutor; }
    public Date getDataAvaliacao() { return dataAvaliacao; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public Double getPercentualGordura() { return percentualGordura; }
    public String getObservacoes() { return observacoes; }

    @Override
    public String toString() {
        String gordura = (percentualGordura != null) ? percentualGordura + "%" : "Não informado";
        return "ID Avaliação: " + idAvaliacao + " | ID Aluno: " + idAluno + " | ID Instrutor: " + idInstrutor +
               " | Data: " + dataAvaliacao + " | Peso: " + peso + "kg | Altura: " + altura + "m | % Gordura: " + gordura +
               " | Obs: " + observacoes;
    }
}