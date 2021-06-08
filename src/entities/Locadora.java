package entities;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;

import application.Programa;

public class Locadora {
	//INCIALIZA OBJETOS DE CLASSES DE ACERVOS DE ARQUIVOS
	public static AcervoFilmes af = new AcervoFilmes("C:\\Users\\breno\\Downloads\\LP2\\Filmes.txt");
	public static AcervoClientes ac = new AcervoClientes("C:\\Users\\breno\\Downloads\\LP2\\Clientes.txt");
	public static AcervoLocacao al = new AcervoLocacao("C:\\Users\\breno\\Downloads\\LP2\\Locacao.txt");
	public static AcervoPagamentos ap = new AcervoPagamentos("C:\\Users\\breno\\Downloads\\LP2\\Pagamentos.txt");
	
	//CADASTRA FILME
	public static boolean cadastraFilme() {
		System.out.println("\n\nInsira o nome do Filme que deseja cadastrar: ");
		try {
			af.cadastrarFilme(new Filme(Programa.sc.nextLine()));
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	//REMOVE FILME
	public static boolean removeFilme() {
		System.out.println("\n\nInsira o nome do Filme que deseja deletar: ");
		try {
			af.excluirFilme(new Filme(Programa.sc.nextLine()));
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	//PESQUISA FILME
	public static boolean pesquisaFilme() {
		System.out.println("\n\nInsira o nome do Filme que deseja procurar: ");
		try {	
			af.pesquisarFilme(Programa.sc.nextLine());
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//CADASTRA CLIENTE
		public static boolean cadastraCliente() {
			System.out.println("\n\nInsira o nome do Cliente que deseja cadastrar: ");
			try {
				ac.cadastrarCliente(new Cliente(Programa.sc.nextLine()));
			}catch(InputMismatchException e) {
				System.out.println("Error: " + e.getMessage());
				return false;
			}
			return true;
		}
	//REMOVE CLIENTE
		public static boolean removeCliente() {
			System.out.println("\n\nInsira o nome do Cliente que deseja deletar: ");
			try {	
				ac.excluirCliente(new Cliente(Programa.sc.nextLine()));
			}catch(InputMismatchException e) {
				System.out.println("Error: " + e.getMessage());
				return false;
			}
			return true;
		}
		//PESQUISA CLIENTE
		public static boolean pesquisaCliente() {
			System.out.println("\n\nInsira o nome do Cliente que deseja procurar: ");
			try {
				ac.pesquisarCliente(Programa.sc.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("Error: " + e.getMessage());
				return false;
			}
			return true;
		}
	
	
	//CADASTRA LOCACAO, CADASTRA PAGAMENTO REFERENTE, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
	public static boolean realizarLocacao() {
		try {
			System.out.println("\n\nInsira o código da Locação: ");
			Integer cod = Programa.sc.nextInt();
			if(al.pesquisarLocacao(cod) != null) {
				System.out.println("ID ja existe para outra locação, cancelando cadastro...");
				return false;
			}
			System.out.println("\n\nInsira o nome do Filme: ");
			Programa.sc.nextLine();
			String filme = Programa.sc.nextLine();
			//VERIFICA SE FILME JÁ FOI ALUGADO
			Filme filme2 = af.pesquisarFilme(filme);
			if(filme2 == null) {
				System.out.println("Filme não existe, cancelando cadastro...");
				return false;
			}
			if(!filme2.getDisponivel()) {
				System.out.println("Filme não disponível, tente com outro, cancelando cadastro...");
				return false;
			}
			
			System.out.println("\n\nInsira o nome do Cliente: ");
			String cliente = Programa.sc.nextLine();
			if(ac.pesquisarCliente(cliente) == null) {
				System.out.println("Cliente não existe na base de dados, cancelando cadastro...");
				return false;
			}
			System.out.println("\n\nInsira a data de devolução \"dd/MM/yyyy\": ");
			String dataDevolucao = Programa.sc.nextLine();
			
			try {
				if(Programa.sdf1.parse(dataDevolucao).before(new Date())) {
					System.out.println("Data de Devolução não pode ser menor que a de hoje, cancelando cadastro...");
					return false;
				}
			} catch (ParseException e) {
				System.out.println("Error: " + e.getMessage());
				System.out.println("Data inserida fora do modelo \"dd/MM/yyyy\", cancelando cadastro...");
				return false;
			}
			System.out.println("\n\nInsira o valor da Locação: ");
			Double valor = Programa.sc.nextDouble();
			al.cadastrarLocacao(new Locacao(cod,filme,cliente,dataDevolucao,valor));
			af.atualizarDisponibilidade(af.pesquisarFilme(filme), false);
			ac.atualizarLocacao(ac.pesquisarCliente(cliente), true);
			ap.cadastrarPagamento(new Pagamento(valor,cod));
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//DELETA LOCACAO, DELETA PAGAMENTO REFERENTE, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
	public static boolean deletarLocacao() {
		try {
			System.out.println("\n\nInsira o código da locação que deseja deletar: ");
			Locacao locacao = al.pesquisarLocacao(Programa.sc.nextInt());
			af.atualizarDisponibilidade(af.pesquisarFilme(locacao.getFilme()), true);
			ac.atualizarLocacao(ac.pesquisarCliente(locacao.getCliente()), ((al.clientePossuiMaisDeUmaLocacao(ac.pesquisarCliente(locacao.getCliente()))) ? true : false));
			ap.excluirPagamento(locacao.getCod());
			al.excluirLocacao(locacao.getCod());
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//PESQUISA LOCACAO
	public static boolean pesquisaLocacao() {
		System.out.println("\n\nInsira o código que deseja procurar: ");
		try {
			al.pesquisarLocacao(Programa.sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//FAZ A DEVOLUÇAO DE UMA LOCACAO, ATUALIZA DATA EM QUE FOI DEVOLVIDA, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
	public static boolean devoluçao() {
		try {
			System.out.println("\n\nInsira o código da Locação para devolução: ");
			Locacao locacao = al.pesquisarLocacao(Programa.sc.nextInt());
			if(locacao.getDataDevolvido().after(locacao.getDataLocacao())) {
				System.out.println("Devolução já foi realizada anteriormente.");
				return false;
			}
			if(locacao == null) {
				System.out.println("Codigo não existe, cancelando devolução.");
				return false;
			}
			al.atualizarDataDevolvido(locacao.getCod());
			System.out.println("\n\nInsira o valor da multa por dia de atraso: ");
			ap.atualizarValorTotal(locacao.getCod(), Programa.sc.nextDouble(), al.totalDiasMulta(locacao.getCod()), locacao.getValor() );
			af.atualizarDisponibilidade(af.pesquisarFilme(locacao.getFilme()), true);
			ac.atualizarLocacao(ac.pesquisarCliente(locacao.getCliente()), ((al.clientePossuiMaisDeUmaLocacao(ac.pesquisarCliente(locacao.getCliente()))) ? true : false));
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	// FAZ O PAGAMENTO, ATUALIZANDO ESTADO DO PAGAMENTO PARA PAGO	
	public static boolean realizaPagamento() {
		System.out.println("\n\nInsira o código da Locação para pagar: ");
		try {
			ap.fecharPagamento(Programa.sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//PESQUISA PAGAMENTO
	public static boolean pesquisaPagamento() {
		System.out.println("\n\nInsira o código do Pagamento que deseja procurar: ");
		try {
			ap.pesquisarPagamento(Programa.sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
}
