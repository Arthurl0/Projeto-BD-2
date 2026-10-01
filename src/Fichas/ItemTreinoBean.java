package Fichas;

public class ItemTreinoBean {
    private int idItem;
    private int idFicha;
    private int idExercicio;
    private String divisaoTreino;
    private int series;
    private int repeticoes;
    private Double carga;

    public ItemTreinoBean(int idItem, int idFicha, int idExercicio, String divisaoTreino, int series, int repeticoes, Double carga) {
        this.idItem = idItem;
        this.idFicha = idFicha;
        this.idExercicio = idExercicio;
        this.divisaoTreino = divisaoTreino;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
    }

    public ItemTreinoBean(int idExercicio, String divisaoTreino, int series, int repeticoes, Double carga) {
        this.idExercicio = idExercicio;
        this.divisaoTreino = divisaoTreino;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
    }

    public int getIdItem() { return idItem; }
    public int getIdFicha() { return idFicha; }
    public void setIdFicha(int idFicha) { this.idFicha = idFicha; }
    public int getIdExercicio() { return idExercicio; }
    public String getDivisaoTreino() { return divisaoTreino; }
    public int getSeries() { return series; }
    public int getRepeticoes() { return repeticoes; }
    public Double getCarga() { return carga; }
}