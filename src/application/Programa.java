package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import entities.Cliente;
import entities.Filme;
import entities.Locacao;
import entities.Pagamento;
import entities.Locadora;
import menu.Menu;
import menu.MenuCliente;
import menu.MenuFilme;
import menu.MenuLocacao;
import menu.MenuPagamento;

public class Programa {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Integer opc = null, opc2 = null;
		
		//INICIA LOOP DO MENU PRINCIPAL
		do {
		
			opc = Menu.menu();
			switch (opc) {
			
			//MENU FILMES
			case 1:
				
				opc2 = MenuFilme.menu();
				switch (opc2) {
				
				//CADASTRA FILMES
				case 1:
					System.out.println("\n\nInsira o nome do Filme que deseja cadastrar: ");
					try {
						Locadora.af.cadastrarFilme(new Filme(sc.nextLine()));
					} catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//REMOVE FILME	
				case 2:
					System.out.println("\n\nInsira o nome do Filme que deseja deletar: ");
					try {
						Locadora.af.excluirFilme(new Filme(sc.nextLine()));
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//PESQUISA FILME
				case 3:
					System.out.println("\n\nInsira o nome do Filme que deseja procurar: ");
					try {	
						Locadora.af.pesquisarFilme(sc.nextLine());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//IMPRIME FILMES QUE PODEM SER LOCADOS	
				case 4:
					Locadora.af.imprimirFilmesDisponiveis();
					break;
				//IMPRIME FILMES QUE ESTAO LOCADOS		
				case 5:
					Locadora.af.imprimirFilmesLocados();
					break;
				//IMPRIME TODOS OS FILMES	
				case 6:
					Locadora.af.imprimirFilmes();
					break;
					
				default:
					System.out.println("Opção Invalida!");
					break;
					
				}
				break;
			// INICIA MENU CLIENTE
			case 2:
				
				opc2 = MenuCliente.menu();
				switch (opc2) {
				//CADASTRA CLIENTE
				case 1:
					System.out.println("\n\nInsira o nome do Cliente que deseja cadastrar: ");
					try {
						Locadora.ac.cadastrarCliente(new Cliente(sc.nextLine()));
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				// REMOVE CLIENTE 
				case 2:
					System.out.println("\n\nInsira o nome do Cliente que deseja deletar: ");
					try {	
						Locadora.ac.excluirCliente(new Cliente(sc.nextLine()));
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//PESQUISA CLIENTE
				case 3:
					System.out.println("\n\nInsira o nome do Cliente que deseja procurar: ");
					try {
						Locadora.ac.pesquisarCliente(sc.nextLine());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//IMPRIME CLIENTES COM LOCAÇÃO EM ABERTO	
				case 4:
					Locadora.ac.imprimirClientesSemPendencia();
					break;
				//IMPRIME CLIENTES SEM LOCAÇÃO EM ABERTO	
				case 5:
					Locadora.ac.imprimirClientesPendentes();
					break;
				//IMPRIME TODOS OS CLIENTES	
				case 6:
					Locadora.ac.imprimirClientes();
					break;
					
				default:
					System.out.println("Opção Invalida!");
					break;
					
				}
				break;
			//INICIA MENU LOCACAO	
			case 3:
				
				opc2 = MenuLocacao.menu();
				switch (opc2) {
				//CADASTRA LOCACAO, CADASTRA PAGAMENTO REFERENTE, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
				case 1:
					try {
						System.out.println("\n\nInsira o código da Locação: ");
						Integer cod = sc.nextInt();
						System.out.println("\n\nInsira o nome do Filme: ");
						sc.nextLine();
						String filme = sc.nextLine();
						//VERIFICA SE FILME JÁ FOI ALUGADO
						Filme filme2 = Locadora.af.pesquisarFilme(filme);
						if(!filme2.getDisponivel()) {
							System.out.println("Filme não disponível, tente com outro.");
							break;
						}
						
						System.out.println("\n\nInsira o nome do Cliente: ");
						String cliente = sc.nextLine();
						System.out.println("\n\nInsira a data de devolução \"dd/MM/yyyy\": ");
						String dataDevolucao = sc.nextLine();
						System.out.println("\n\nInsira o valor da Locação: ");
						Double valor = sc.nextDouble();
						Locadora.al.cadastrarLocacao(new Locacao(cod,filme,cliente,dataDevolucao,valor));
						Locadora.af.atualizarDisponibilidade(Locadora.af.pesquisarFilme(filme), false);
						Locadora.ac.atualizarLocacao(Locadora.ac.pesquisarCliente(cliente), true);
						Locadora.ap.cadastrarPagamento(new Pagamento(valor,cod));
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					
					
					break;
				//DELETA LOCACAO, DELETA PAGAMENTO REFERENTE, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
				case 2:
					try {
						System.out.println("\n\nInsira o código da locação que deseja deletar: ");
						Locacao locacao = Locadora.al.pesquisarLocacao(sc.nextInt());
						Locadora.af.atualizarDisponibilidade(Locadora.af.pesquisarFilme(locacao.getFilme()), true);
						Locadora.ac.atualizarLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()), false);
						Locadora.ap.excluirPagamento(locacao.getCod());
						Locadora.al.excluirLocacao(locacao.getCod());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//PESQUISA LOCACAO 
				case 3:
					System.out.println("\n\nInsira o código que deseja procurar: ");
					try {
						Locadora.al.pesquisarLocacao(sc.nextInt());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//IMPRIME LOCACOES DE CLIENTES EM ATRASO DE DEVOLUCAO 
				case 4:
					Locadora.al.imprimirLocacaosPendentes();
					break;
				//IMPRIME LOCACOES EM DIA
				case 5:
					Locadora.al.imprimirLocacaosSemPendencia();
					break;
				//IMPRIME TODAS LOCACOES	
				case 6:
					Locadora.al.imprimirLocacaos();
					break;
					
				default:
					System.out.println("Opção Invalida!");
					break;
					
				}
				break;
			//INICIA MENU DEVOLUCAO/PAGAMENTO
			case 4:
				
				opc2 = MenuPagamento.menu();
				switch (opc2) {
				//FAZ A DEVOLUÇAO DE UMA LOCACAO, ATUALIZA DATA EM QUE FOI DEVOLVIDA, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
				case 1:
					try {
						System.out.println("\n\nInsira o código da Locação para devolução: ");
						Locacao locacao = Locadora.al.pesquisarLocacao(sc.nextInt());
						Locadora.al.atualizarDataDevolvido(locacao.getCod());
						System.out.println("\n\nInsira o valor da multa por dia de atraso: ");
						Locadora.ap.atualizarValorTotal(locacao.getCod(), sc.nextDouble(), Locadora.al.totalDiasMulta(locacao.getCod()), locacao.getValor() );
						Locadora.af.atualizarDisponibilidade(Locadora.af.pesquisarFilme(locacao.getFilme()), true);
						Locadora.ac.atualizarLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()), false);
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				// FAZ O PAGAMENTO, ATUALIZANDO ESTADO DO PAGAMENTO PARA PAGO	
				case 2:
					System.out.println("\n\nInsira o código da Locação para pagar: ");
					try {
						Locadora.ap.fecharPagamento(sc.nextInt());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//PESQUISA PAGAMENTO
				case 3:
					System.out.println("\n\nInsira o código do Pagamento que deseja procurar: ");
					try {
						Locadora.ap.pesquisarPagamento(sc.nextInt());
					}catch(InputMismatchException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				//IMPRIME PAGAMENTOS QUE FORAM PAGOS	
				case 4:
					Locadora.ap.imprimirPagamentosSemPendencia();
					break;
				//IMPRIME PAGAMENTOS QUE NAO FORAM PAGOS
				case 5:
					Locadora.ap.imprimirPagamentosPendentes();
					break;
				//IMPRIME TODOS OS PAGAMENTOS	
				case 6:
					Locadora.ap.imprimirPagamentos();
					break;
					
				default:
					System.out.println("Opção Invalida!");
					break;
					
				}
				break;
				
				
			default:
				break;
			}
				
		}while(opc != 5);
	
	}

}
