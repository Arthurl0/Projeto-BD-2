package Fichas;

import java.sql.Date;

public class FichasBean {
    private int idFicha;
    private int idAluno;
    private int idInstrutor;
    private Date dataInicio;
    private Date dataFim;
    private String objetivo;

    public FichasBean(int idFicha, int idAluno, int idInstrutor, Date dataInicio, Date dataFim, String objetivo) {
        this.idFicha = idFicha;
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.objetivo = objetivo;
    }

    public FichasBean(int idAluno, int idInstrutor, Date dataInicio, Date dataFim, String objetivo) {
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.objetivo = objetivo;
    }

    public int getIdFicha() { return idFicha; }
    public void setIdFicha(int idFicha) { this.idFicha = idFicha; }
    public int getIdAluno() { return idAluno; }
    public int getIdInstrutor() { return idInstrutor; }
    public Date getDataInicio() { return dataInicio; }
    public Date getDataFim() { return dataFim; }
    public String getObjetivo() { return objetivo; }

    @Override
    public String toString() {
        return "ID Ficha: " + idFicha + " | ID Aluno: " + idAluno + " | ID Instrutor: " + idInstrutor +
               " | Início: " + dataInicio + " | Fim: " + dataFim + " | Objetivo: " + objetivo;
    }
}