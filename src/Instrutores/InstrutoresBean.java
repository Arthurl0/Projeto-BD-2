package Instrutores;

public class InstrutoresBean {
    private int idInstrutor;
    private String cpf;
    private String cref;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String especialidade;

    public InstrutoresBean(int idInstrutor, String cpf, String cref, String nome, String telefone, String email, String endereco, String especialidade) {
        this.idInstrutor = idInstrutor;
        this.cpf = cpf;
        this.cref = cref;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.especialidade = especialidade;
    }

    public InstrutoresBean(String cpf, String cref, String nome, String telefone, String email, String endereco, String especialidade) {
        this.cpf = cpf;
        this.cref = cref;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.especialidade = especialidade;
    }

    public int getIdInstrutor() { return idInstrutor; }
    public void setIdInstrutor(int idInstrutor) { this.idInstrutor = idInstrutor; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCref() { return cref; }
    public void setCref(String cref) { this.cref = cref; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public String toString() {
        return "ID: " + idInstrutor + " | Nome: " + nome + " | CREF: " + cref + " | CPF: " + cpf + 
               " | Tel: " + telefone + " | Especialidade: " + especialidade + " | E-mail: " + email;
    }
}