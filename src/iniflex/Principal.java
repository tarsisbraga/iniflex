package iniflex;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.text.NumberFormat;


public class Principal {

	private static List<Funcionario> funcionarios = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static NumberFormat formato = NumberFormat.getNumberInstance(Locale.GERMAN);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
		

        int opcao;
        
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                	removerFuncionario(); //3.2 – Remover o funcionário “João” da lista. (esse método dá opção de excluir quem quiser da lista, inclusive o João)
                    break;
                case 2:
                	listarFuncionarios(); //3.3 – Imprimir todos os funcionários com todas suas informações
                    break;                
                case 3:
                	aumentarSalario(); //3.4 – Os funcionários receberam 10% de aumento de salário
                	break;
                case 4:
                	agruparFuncionarios(); //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. e 3.6 – Imprimir os funcionários, agrupados por função.
                	break;
                case 5:
                	listarPorMesAniversario(); //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
                	break;
                case 6:
                	listarFuncionarioMaisVelho(); //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
                	break;
                case 7:
                	listarOrdemAlfabetica(); //3.10 – Imprimir a lista de funcionários por ordem alfabética.
                	break;
                case 8:
                	mostrarSalarioTotal(); //3.11 – Imprimir o total dos salários dos funcionários.
                	break;
                case 9:
                	qtdSalariosMinimos(); //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
                	break;
                case 13:
                	cadastrarNovoFuncionario(scanner, funcionarios); //EXTRA
                	break;
                case 0:
                    System.out.println("Encerrando o programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();

	}
	
	private static void exibirMenu() {
        System.out.println("===== Menu =====");
        System.out.println("1. Remover funcionário");
        System.out.println("2. Listar funcionários");
        System.out.println("3. Aumentar salário de todos");
        System.out.println("4. Agrupar funcionários por função");
        System.out.println("5. Listar funcionários por mês de aniversário");
        System.out.println("6. Listar funcionário mais velho");
        System.out.println("7. Listar funcionários por orfem alfabética");
        System.out.println("8. Mostrar total dos salários dos funcionários");
        System.out.println("9. Quantidade de salários mínimos por funcionários");
        System.out.println("13. Cadastrar novo funcionário"); //EXTRA
        System.out.println("0. Sair");
    }
	
	private static void cadastrarNovoFuncionario(Scanner scanner, List<Funcionario> funcionarios) {
        System.out.print("Digite o nome do novo funcionário: ");
        String nome = scanner.next();

        System.out.print("Digite a data de nascimento do novo funcionário (no formato aaaa-mm-dd): ");
        String dataNascimentoStr = scanner.next();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

        System.out.print("Digite o salário do novo funcionário (no formato ###,##): ");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.print("Digite a função do novo funcionário: ");
        String funcao = scanner.next();

        Funcionario novoFuncionario = new Funcionario(nome, dataNascimento, salario, funcao);
        funcionarios.add(novoFuncionario);

        System.out.println("Novo funcionário cadastrado com sucesso!");
    } 
	
	private static void listarFuncionarios() {
		int i=0;
        for (Funcionario funcionario : funcionarios) {
        	formato.setMinimumFractionDigits(2);
            formato.setMaximumFractionDigits(2);
            System.out.println("Nome: "+funcionario.getNome() + " | Data Nascimento: " + funcionario.getDataNascimento().format(formatter) + " | Salário: "+formato.format(funcionario.getSalario()) + " | Função: "+ funcionario.getFuncao()) ;
            i++;
        }
        if (i==0) {
        	System.out.println("Lista de funcionários vazia!");
		}
    }
	
	private static void removerFuncionario() {
	    if (funcionarios.isEmpty()) {
	        System.out.println("Não há funcionários para remover.");
	        return;
	    }

	    System.out.println("===== Lista de Funcionários =====");
	    for (int i = 0; i < funcionarios.size(); i++) {
	        System.out.println(i + ". " + funcionarios.get(i).getNome());
	    }

	    System.out.print("Digite o índice do funcionário que deseja remover: ");
	    int indice = scanner.nextInt();

	    if (indice >= 0 && indice < funcionarios.size()) {
	        Funcionario funcionarioRemovido = funcionarios.remove(indice);
	        System.out.println("Funcionário removido com sucesso: " + funcionarioRemovido.getNome());
	    } else {
	        System.out.println("Índice inválido. Nenhum funcionário removido.");
	    }
	}
	
	private static void aumentarSalario() {
		
		System.out.print("Digite a porcentagem que deseja aumentar para os funcionários: ");
		Float valor = scanner.nextFloat();
		Float valorDecimal = valor / 100;
		for (Funcionario funcionario : funcionarios) {
            funcionario.ajustarSalario(valorDecimal);
        }
		System.out.println("Foi aplicado " + valor + "% ao salário de todos");
	}
	
	private static void agruparFuncionarios() {		
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("    " + funcionario.getNome());
            }
        }
	}
	
	private static void listarPorMesAniversario() {
		int i = 0;
		
		System.out.print("Digite o número mês de aniversário que deseja listar os funcionários (entre 1 e 12): ");
        int mesAniversarioDesejado = scanner.nextInt(); 

        List<Funcionario> aniversariantesDoMes = funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mesAniversarioDesejado)
                .collect(Collectors.toList());

        String mesEmPortugues = obterNomeMesEmPortugues(mesAniversarioDesejado);
        System.out.println("Funcionários que fazem aniversário em " + mesEmPortugues + ":");
        for (Funcionario funcionario : aniversariantesDoMes) {
            System.out.println(funcionario.getNome() + " - " + funcionario.getDataNascimento().format(formatter));
            i++;
        }
        
        if (i == 0) {
        	System.out.println("Não existe funcionários que façam aniversário nesse mês.");
		}
	}
	
	private static String obterNomeMesEmPortugues(int numeroMes) {
        String[] mesesEmPortugues = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        if (numeroMes >= 1 && numeroMes <= 12) {
            return mesesEmPortugues[numeroMes - 1];
        } else {
            return "Mês inválido";
        }
    }
	
	private static void listarFuncionarioMaisVelho() {
		Funcionario funcionarioMaisVelho = encontrarFuncionarioMaisVelho();
        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + calcularIdade(funcionarioMaisVelho.getDataNascimento()));
        } else {
            System.out.println("Lista de funcionários vazia.");
        }
	}
	
	private static Funcionario encontrarFuncionarioMaisVelho() {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
    
    private static void listarOrdemAlfabetica() {
    	funcionarios.stream()
        .sorted(Comparator.comparing(Funcionario::getNome))
        .forEach(funcionario -> System.out.println("Nome: "+funcionario.getNome() + " | Data Nascimento: " + funcionario.getDataNascimento().format(formatter) + " | Salário: "+formato.format(funcionario.getSalario()) + " | Função: "+ funcionario.getFuncao()));    	
    }
    
    private static void mostrarSalarioTotal() {
    	BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    	System.out.println("Valor total de todos os salários é: R$ " + formato.format(totalSalarios) );
    }
    
    private static void qtdSalariosMinimos() {
    	BigDecimal salarioMinimo = new BigDecimal("1212.00");

        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        });
    }

}
