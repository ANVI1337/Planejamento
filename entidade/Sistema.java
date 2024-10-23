package entidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Aluno> alunos;
	private Scanner input;

	// Construtor da classe sistema
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.input = new Scanner(System.in);
	}

	// Método para cadastrar um novo aluno.
	public String cadastrarAluno(String nome, double[] notas) {
		// Verificar se já existe um aluno com o mesmo nome.
		for (Aluno aluno : alunos) {
			if (aluno.getNome().equalsIgnoreCase(nome)) {
				return "Erro: já existe um aluno cadastrado com esse nome";
			}
		}

		Aluno novoAluno = new Aluno(nome, notas);
		alunos.add(novoAluno);
		return "Aluno cadastrado com sucesso";
	}

	// metodo para obter um aluno pelo nome
	public Aluno getAlunoPorNome(String nome) {
		for (Aluno aluno : alunos) {
			if (aluno.getNome().equalsIgnoreCase(nome)) {
				return aluno;
			}
		}
		return null; // retorna null se não encontrar o aluno.
	}

	// metodo para editar o nome do aluno
	public void editarNomeAluno(String nomeAtual, String novoNome) {
		Aluno aluno = getAlunoPorNome(nomeAtual);
		if (aluno != null) {
			// Verificar se já existe um aluno com o mesmo nome novo para evitar duplicidade
			if (getAlunoPorNome(novoNome) != null) {
				System.out.println("Erro: Já existe um aluno com o nome '" + novoNome + "'");
			} else {
				aluno.setNome(novoNome);
				System.out.println("Nome do aluno alterado com sucesso.");
			}

		}else {
			System.out.println("Aluno não encontrado.");
		}
	}
	
	// Método para editar nota de um aluno.
	public void editarNotaAluno(String nome, int indexNota, double novaNota) {
		Aluno aluno = getAlunoPorNome(nome);
		if(aluno != null) {
			aluno.editarNota(indexNota, novaNota);
			System.out.println("Nota alterada com sucesso.");
		}else {
			System.out.println("Aluno não encontrado.");
		}
	}
	
	// Método para gerar relatório com nome, notas e situação de aprovação 
	public String gerarRelatório() {
		StringBuilder relatorio = new StringBuilder();
		for (Aluno aluno : alunos) {
		relatorio.append("Nome: ").append(aluno.getNome()).append("\n");
		relatorio.append("Notas: ").append(aluno.getNotas()).append("\n");
		relatorio.append("Media: ").append(aluno.calcularMedia()).append("\n");
		relatorio.append("Situação: ").append(aluno.estaAprovado() ? "Aprovado" : "Reprovado").append("\n");
		relatorio.append("-------------------------------------------------\n");
		}
		return relatorio.toString();
	}
	
	//Método para exibir o menu de opções.
	public void exibirMenu() {
		int opcao;
		
		do {
			System.out.println("\nMenu: ");
			System.out.println("1. Cadastrar  aluno");
			System.out.println("2. Editar nome do aluno");
			System.out.println("3. Editar nota do aluno");
			System.out.println("4. Gerar Relatório");
			System.out.println("5. Sair");
			System.out.println("Escolha uma opção: ");
			opcao = input.nextInt();
			input.nextLine(); // Consumir a quebra de linha após o número.
			
			switch (opcao) {
			case 1:
				cadastrarAlunoMenu();
				break;
			case 2: 
				editarNomeMenu();
				break;
			case 3: 
				editarNotaMenu();
				break;
			case 4: 
				System.out.println(gerarRelatório());
				break;
			case 5:
				System.out.println("Sair...");
				break;
			default:
				System.out.println("Opção inválida. Tente Novamente.");
				break;
			}
		}while(opcao != 5);
	}
	// Método auxiliar para cadastrar aluno via menu .
	private void cadastrarAlunoMenu() {
		System.out.println("Digite o nome do aluno: ");
		String nome = input.nextLine();
		
		double[] notas = new double [4];
		for(int i = 0; i < 4; i++) {
			System.out.println("Digite a nota" + (i + 1) + ": ");
			notas[i] = input.nextDouble();
		}
		String resultado = cadastrarAluno(nome, notas);
		System.out.println(resultado);
	}
	// Método auxiliar para editar o nome de um aluno via menu.
	private void editarNomeMenu() {
		System.out.println("Digite o nome atual do aluno: ");
		String nomeAtual = input.nextLine();
		
		System.out.println("Digite o novo nome do aluno: ");
		String novoNome = input.nextLine();
		
		editarNomeAluno(nomeAtual, novoNome);

	}
	
	// Método auxiliar para editar as notas de um aluno via menu.
	private void editarNotaMenu() {
		System.out.println("Digite o nome do aluno: ");
		String nome = input.nextLine();
		
		System.out.println("Digite o número da nota que deseja editar (1 a 4): ");
		int numeroNota = input.nextInt();
		
		System.out.println("Digite o novo valor da nota: ");
		double novaNota = input.nextDouble();
		
		editarNotaAluno(nome, numeroNota, novaNota);
	}
}