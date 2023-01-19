import java.time.LocalDate;
import java.time.Period;

public class Animal {
    private Integer codigo;
    private String nome;
    private Especie especie;
    private LocalDate dataDeNascimento;
    private String cor;
    private Temperamento temperamento;
    private String nomeDoResponsavel;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor.toUpperCase();
    }

    public Temperamento getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(Temperamento temperamento) {
        this.temperamento = temperamento;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel.toUpperCase();
    }

    public Integer calculaIdade() {
        LocalDate dataDeHoje = LocalDate.now();
        Period idade = Period.between(dataDeNascimento, dataDeHoje);
        return idade.getYears();
    }
}
