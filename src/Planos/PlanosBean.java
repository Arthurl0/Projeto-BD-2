package Planos;

public class PlanosBean {
    private int idPlano;
    private double preco;
    private String descricao;
    private String nomePlano;
    private int duracao;

    public PlanosBean(int idPlano, double preco, String descricao, String nomePlano, int duracao) {
        this.idPlano = idPlano;
        this.preco = preco;
        this.descricao = descricao;
        this.nomePlano = nomePlano;
        this.duracao = duracao;
    }

    public PlanosBean(double preco, String descricao, String nomePlano, int duracao) {
        this.preco = preco;
        this.descricao = descricao;
        this.nomePlano = nomePlano;
        this.duracao = duracao;
    }

    public int getIdPlano() { return idPlano; }
    public void setIdPlano(int idPlano) { this.idPlano = idPlano; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getNomePlano() { return nomePlano; }
    public void setNomePlano(String nomePlano) { this.nomePlano = nomePlano; }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }

    @Override
    public String toString() {
        return "ID: " + idPlano + " | Plano: " + nomePlano + " | Preço: R$ " + String.format("%.2f", preco) + 
               " | Duração: " + duracao + " dias | Descrição: " + descricao;
    }
}