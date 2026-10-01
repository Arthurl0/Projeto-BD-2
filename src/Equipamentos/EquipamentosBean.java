package Equipamentos;

public class EquipamentosBean {
    private int idEquipamento;
    private String nome;
    private String marca;
    private String status;

    public EquipamentosBean(int idEquipamento, String nome, String marca, String status) {
        this.idEquipamento = idEquipamento;
        this.nome = nome;
        this.marca = marca;
        this.status = status;
    }

    public EquipamentosBean(String nome, String marca, String status) {
        this.nome = nome;
        this.marca = marca;
        this.status = status;
    }

    public int getIdEquipamento() { return idEquipamento; }
    public void setIdEquipamento(int idEquipamento) { this.idEquipamento = idEquipamento; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ID: " + idEquipamento + " | Nome: " + nome + " | Marca: " + marca + " | Status: " + status;
    }
}