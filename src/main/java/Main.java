import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public static ArrayList<Agendamento> todosOsAgendamentos = new ArrayList<>();

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
                case 2 -> agendamentoDeServico();
                case 3 -> listagemAnimaisCadastrados();
                case 4 -> listagemDeAgendamentos();
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

        System.out.println("Digite a data de nascimento do animal (DD/MM/AAAA): ");
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
            System.out.print(animal.getCodigo() + "|\t");
            System.out.print(animal.getNome() + "|\t");
            System.out.print(animal.getEspecie() + "|\t");
            System.out.print(animal.calculaIdade() + "|\t");
            System.out.print(animal.getCor() + "|\t");
            System.out.print(animal.getTemperamento() + "|\t");
            System.out.println(animal.getNomeDoResponsavel());
        }
    }

    public static void agendamentoDeServico(){
        Scanner ler = new Scanner(System.in);
        Agendamento agendamento = new Agendamento();
        Animal animal = new Animal();
        boolean excecao;
        Integer opcao = 2;
        Optional<Animal> animalSelecionado;

        System.out.println("Agendamento de serviços");
        System.out.println("Segue a lista de animais cadastrados no SisPet");
        listagemAnimaisCadastrados();

        do{
            System.out.println("Informe o código do animal selecionado: ");
            Integer codAnimalSelecionado = Integer.parseInt(ler.nextLine());
            animalSelecionado = todosOsAnimais.stream().filter(animal1 -> animal1.getCodigo() == codAnimalSelecionado).findFirst();
            if (animalSelecionado.isEmpty()) {
                System.out.println("Código Inválido");
            }
        }while (animalSelecionado.isEmpty());

        agendamento.setAnimal(animalSelecionado.get());

        do{
            System.out.println("Segue todos os serviços disponíveis para o Pet: ");
            Stream.of(Servico.values()).forEach(System.out::println);
            Servico servicoSelecionado = null;
            do {
                System.out.println("Digite o serviço desejado: ");
                String servicoDigitado = ler.nextLine();
                servicoDigitado = servicoDigitado.toUpperCase();
                try{
                    excecao = false;
                    servicoSelecionado = Servico.valueOf(servicoDigitado);
                }catch (Exception exception){
                    excecao = true;
                    System.out.println("Opção inválida");
                }
            }while (excecao);
            agendamento.setServicos(servicoSelecionado);
            System.out.println("Digite 1 para adicionar mais serviços e 2 caso contrário: ");
            opcao = Integer.parseInt(ler.nextLine());
        }while (opcao == 1);

        System.out.println("Digite a data de inicio do serviço (DD/MM/AAAA HH:MM): ");
        String horarioDoInicioDoServico = ler.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        LocalDateTime horarioDeIncioDoServicoFormat = LocalDateTime.parse(horarioDoInicioDoServico, formato);
        agendamento.setHorarioInical(horarioDeIncioDoServicoFormat);

        System.out.println("Digite a data de termino do serviço: ");
        String horarioDoTerminoDoServico = ler.nextLine();
        LocalDateTime horarioDeTerminoDoServicoFormat = LocalDateTime.parse(horarioDoTerminoDoServico, formato);
        agendamento.setHorarioFinal(horarioDeTerminoDoServicoFormat);

        System.out.println("Observações: ");
        agendamento.setObservacoes(ler.nextLine());

        todosOsAgendamentos.add(agendamento);
    }

    public static void listagemDeAgendamentos(){
        for(Agendamento a : todosOsAgendamentos){
            System.out.print(a.getHorarioInical() + " |\t");
            System.out.print(a.getHorarioFinal() + " |\t");
            System.out.print(a.getServicos() + " |\t");
            System.out.print(a.getAnimal().getNome() + " |\t");
            System.out.print(a.getAnimal().getNomeDoResponsavel() + " |\t");
            System.out.println(a.getObservacoes());
        }
    }

}

