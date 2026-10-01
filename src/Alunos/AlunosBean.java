package Alunos;
import java.sql.Date;

public class AlunosBean {
    private int idAluno;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String email;
    private String cpf;
    private String endereco;

    public AlunosBean(int idAluno, String nome, String telefone, Date dataNascimento, String email, String cpf, String endereco) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public AlunosBean(String nome, String telefone, Date dataNascimento, String email, String cpf, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    public int getIdAluno() {return idAluno;}
    public void setIdAluno(int idAluno) {this.idAluno = idAluno;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public Date getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(Date dataNascimento) {this.dataNascimento = dataNascimento;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco;}

    @Override
    public String toString() {
        return "ID: " + idAluno + " | Nome: " + nome + " | CPF: " + cpf + 
               " | Tel: " + telefone + " | Email: " + email + " | Data Nasc: " + dataNascimento + " | End: " + endereco;
    }
}