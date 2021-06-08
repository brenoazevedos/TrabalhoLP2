package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Cliente;
import entities.Filme;
import entities.Locacao;
import entities.Locadora;
import entities.Pagamento;
import menu.Menu;
import menu.MenuCliente;
import menu.MenuFilme;
import menu.MenuLocacao;
import menu.MenuPagamento;

public class Programa {
	public static Scanner sc = new Scanner(System.in);
	public static SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		Integer opc = null, opc2 = null;
		
		//INICIA LOOP DO MENU PRINCIPAL
		do {
		
			opc = Menu.menu();
			switch (opc) {
			
			//MENU FILMES
			case 1:
				do {
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
						
					case 7:
						break;
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 7);
				break;
			// INICIA MENU CLIENTE
			case 2:
				do {
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
					//VOLTA AO MENU PRINCIPAL
					case 7:
						break;
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 7);
				break;
			//INICIA MENU LOCACAO	
			case 3:
				do {
					opc2 = MenuLocacao.menu();
					switch (opc2) {
					//CADASTRA LOCACAO, CADASTRA PAGAMENTO REFERENTE, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
					case 1:
						try {
							System.out.println("\n\nInsira o código da Locação: ");
							Integer cod = sc.nextInt();
							if(Locadora.al.pesquisarLocacao(cod) != null) {
								System.out.println("ID ja existe para outra locação, cancelando cadastro...");
								break;
							}
							System.out.println("\n\nInsira o nome do Filme: ");
							sc.nextLine();
							String filme = sc.nextLine();
							//VERIFICA SE FILME JÁ FOI ALUGADO
							Filme filme2 = Locadora.af.pesquisarFilme(filme);
							if(filme2 == null) {
								System.out.println("Filme não existe, cancelando cadastro...");
								break;
							}
							if(!filme2.getDisponivel()) {
								System.out.println("Filme não disponível, tente com outro, cancelando cadastro...");
								break;
							}
							
							System.out.println("\n\nInsira o nome do Cliente: ");
							String cliente = sc.nextLine();
							if(Locadora.ac.pesquisarCliente(cliente) == null) {
								System.out.println("Cliente não existe na base de dados, cancelando cadastro...");
								break;
							}
							System.out.println("\n\nInsira a data de devolução \"dd/MM/yyyy\": ");
							String dataDevolucao = sc.nextLine();
							
							try {
								if(sdf1.parse(dataDevolucao).before(new Date())) {
									System.out.println("Data de Devolução não pode ser menor que a de hoje, cancelando cadastro...");
									break;
								}
							} catch (ParseException e) {
								System.out.println("Error: " + e.getMessage());
								System.out.println("Data inserida fora do modelo \"dd/MM/yyyy\", cancelando cadastro...");
								break;
							}
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
							Locadora.ac.atualizarLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()), ((Locadora.al.clientePossuiMaisDeUmaLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()))) ? true : false));
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
						
					//IMPRIME TODAS LOCACOES	
					case 7:
						Locadora.al.imprimirLocacaosDevolvidas();
						break;
					//VOLTA AO MENU PRINCIPAL
					case 8:
						break;
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 8);
				break;
			//INICIA MENU DEVOLUCAO/PAGAMENTO
			case 4:
				do {
					opc2 = MenuPagamento.menu();
					switch (opc2) {
					//FAZ A DEVOLUÇAO DE UMA LOCACAO, ATUALIZA DATA EM QUE FOI DEVOLVIDA, ATUALIZA ESTADO DO FILME E ESTADO DO CLIENTE
					case 1:
						try {
							System.out.println("\n\nInsira o código da Locação para devolução: ");
							Locacao locacao = Locadora.al.pesquisarLocacao(sc.nextInt());
							if(locacao.getDataDevolvido().after(locacao.getDataLocacao())) {
								System.out.println("Devolução já foi realizada anteriormente.");
								break;
							}
							else if(locacao == null) {
								System.out.println("Codigo não existe, cancelando devolução.");
								break;
							}
							Locadora.al.atualizarDataDevolvido(locacao.getCod());
							System.out.println("\n\nInsira o valor da multa por dia de atraso: ");
							Locadora.ap.atualizarValorTotal(locacao.getCod(), sc.nextDouble(), Locadora.al.totalDiasMulta(locacao.getCod()), locacao.getValor() );
							Locadora.af.atualizarDisponibilidade(Locadora.af.pesquisarFilme(locacao.getFilme()), true);
							Locadora.ac.atualizarLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()), ((Locadora.al.clientePossuiMaisDeUmaLocacao(Locadora.ac.pesquisarCliente(locacao.getCliente()))) ? true : false));
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
					
					//VOLTA AO MENU PRINCIPAL
					case 7:
						break;	
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 7);
				break;
				
				
			default:
				System.out.println("Opção Invalida!");
				break;
			}
				
		}while(opc != 5);
		System.out.println("\n\nEncerrando...\nAté Logo!!");
	}

}
