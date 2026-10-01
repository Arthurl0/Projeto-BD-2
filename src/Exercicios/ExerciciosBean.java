package Exercicios;

public class ExerciciosBean {
    private int idExercicio;
    private String nomeExercicio;
    private String grupoMuscular;
    private Integer idEquipamento;

    public ExerciciosBean(int idExercicio, String nomeExercicio, String grupoMuscular, Integer idEquipamento) {
        this.idExercicio = idExercicio;
        this.nomeExercicio = nomeExercicio;
        this.grupoMuscular = grupoMuscular;
        this.idEquipamento = idEquipamento;
    }

    public ExerciciosBean(String nomeExercicio, String grupoMuscular, Integer idEquipamento) {
        this.nomeExercicio = nomeExercicio;
        this.grupoMuscular = grupoMuscular;
        this.idEquipamento = idEquipamento;
    }

    public int getIdExercicio() { return idExercicio; }
    public void setIdExercicio(int idExercicio) { this.idExercicio = idExercicio; }
    public String getNomeExercicio() { return nomeExercicio; }
    public void setNomeExercicio(String nomeExercicio) { this.nomeExercicio = nomeExercicio; }
    public String getGrupoMuscular() { return grupoMuscular; }
    public void setGrupoMuscular(String grupoMuscular) { this.grupoMuscular = grupoMuscular; }
    public Integer getIdEquipamento() { return idEquipamento; }
    public void setIdEquipamento(Integer idEquipamento) { this.idEquipamento = idEquipamento; }

    @Override
    public String toString() {
        String equip = (idEquipamento == null || idEquipamento == 0) ? "Nenhum (Peso Corporal)" : "ID Equipamento: " + idEquipamento;
        return "ID: " + idExercicio + " | Exercício: " + nomeExercicio + " | Grupo Muscular: " + grupoMuscular + " | " + equip;
    }
}