import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        menuInicial();
    }
    public static Integer proxCodAnimal = 0;

    public static ArrayList<Animal> todosOsAnimais = new ArrayList<>();

    public static void menuInicial() {
        Scanner ler = new Scanner(System.in);
        Integer opcao = 0;
        do {
            System.out.println("Bem-vindo ao SisPet");
            System.out.println("1 - Cadastrar animal");
            System.out.println("2 - Agendar serviço");
            System.out.println("3 - Listar animais cadastrados");
            System.out.println("4 - Listar agenda");
            System.out.println("5 - Sair");
            System.out.println();
            System.out.println("Digite a opção desejada: ");
            opcao = Integer.parseInt(ler.nextLine());
            switch (opcao) {
                case 1 -> cadastroDeAnimal();
                //case 2 -> ;
                case 3 -> listagemAnimaisCadastrados();
                //case 4 -> ;
            }
        } while (opcao != 5);
    }

    public static void cadastroDeAnimal() {
        Scanner ler = new Scanner(System.in);
        Animal animal = new Animal();
        animal.setCodigo(proxCodAnimal += 1);

        System.out.println("Cadastro de Animal");
        System.out.println("Digite o nome do resposavel pelo animal: ");
        animal.setNomeDoResponsavel(ler.nextLine());

        System.out.println("Digite o nome do Animal: ");
        animal.setNome(ler.nextLine());

        System.out.println("Digite a data de nascimento do animal: ");
        String dataDeNascimentoDigitada = ler.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate dataNascimentoFormatada = LocalDate.parse(dataDeNascimentoDigitada, formato);
        animal.setDataDeNascimento(dataNascimentoFormatada);

        System.out.println("Digite a cor do animal: ");
        animal.setCor(ler.nextLine());

        animal.setEspecie(selecaoDeEspecie());
        animal.setTemperamento(selecaoDeTemperamento());
        todosOsAnimais.add(animal);
    }

    public static Especie selecaoDeEspecie(){
        Scanner ler = new Scanner(System.in);
        System.out.println("Segue todas as especies disponíveis para cadastro: ");
        Stream.of(Especie.values()).forEach(System.out::println);
        boolean excecao;
        Especie especieSelecionada = null;

        do {
            System.out.println("Digite a especie desejada: ");
            String especieDigitada = ler.nextLine();
            especieDigitada = especieDigitada.toUpperCase();
            try{
                excecao = false;
                especieSelecionada = Especie.valueOf(especieDigitada);

            }catch (Exception exception){
                excecao = true;
                System.out.println("Opção inválida");
            }
        }while (excecao);
        return especieSelecionada;
    }

    public static Temperamento selecaoDeTemperamento(){
        Scanner ler = new Scanner(System.in);
        System.out.println("Segue todas os temperamentos disponíveis para cadastro: ");
        Stream.of(Temperamento.values()).forEach(System.out::println);
        boolean excecao;
        Temperamento temperamentoSelecionado = null;

        do {
            System.out.println("Digite o temperamento desejado: ");
            String temperamentoDigitado = ler.nextLine();
            temperamentoDigitado = temperamentoDigitado.toUpperCase();
            try{
                excecao = false;
                temperamentoSelecionado = Temperamento.valueOf(temperamentoDigitado);

            }catch (Exception exception){
                excecao = true;
                System.out.println("Opção inválida");
            }
        }while (excecao);
        return temperamentoSelecionado;
    }

    public static void listagemAnimaisCadastrados(){
        for(Animal animal : todosOsAnimais){
            System.out.print(animal.getCodigo() + "\t");
            System.out.print(animal.getNome() + "\t");
            System.out.print(animal.getEspecie() + "\t");
            System.out.print(animal.calculaIdade() + "\t");
            System.out.print(animal.getCor() + "\t");
            System.out.print(animal.getTemperamento() + "\t");
            System.out.println(animal.getNomeDoResponsavel());
        }
    }

}

