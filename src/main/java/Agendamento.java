import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Agendamento {

    private LocalDateTime horarioInical;

    private LocalDateTime horarioFinal;

    private ArrayList<Servico> servicos = new ArrayList<>();

    private Animal animal;

    private String observacoes;

    public LocalDateTime getHorarioInical() {
        return horarioInical;
    }

    public void setHorarioInical(LocalDateTime horarioInical) {
        this.horarioInical = horarioInical;
    }

    public LocalDateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalDateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(Servico servico) {
        this.servicos.add(servico);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
