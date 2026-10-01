package Matricula;
import java.sql.Date;

public class MatriculaBean {
    private int idMatricula;
    private int idAluno;
    private int idPlano;
    private Date dataInicio;
    private Date dataVencimento;
    private String status;

    public MatriculaBean(int idMatricula, int idAluno, int idPlano, Date dataInicio, Date dataVencimento, String status) {
        this.idMatricula = idMatricula;
        this.idAluno = idAluno;
        this.idPlano = idPlano;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public MatriculaBean(int idAluno, int idPlano, Date dataInicio, Date dataVencimento, String status) {
        this.idAluno = idAluno;
        this.idPlano = idPlano;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public int getIdMatricula() { return idMatricula; }
    public int getIdAluno() { return idAluno; }
    public int getIdPlano() { return idPlano; }
    public Date getDataInicio() { return dataInicio; }
    public Date getDataVencimento() { return dataVencimento; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Matrícula ID: " + idMatricula + " | ID Aluno: " + idAluno + " | ID Plano: " + idPlano +
               " | Início: " + dataInicio + " | Vencimento: " + dataVencimento + " | Status: " + status;
    }
}