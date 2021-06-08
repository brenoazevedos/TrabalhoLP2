package application;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import entities.Locadora;
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
					
	
					case 1:
						Locadora.cadastraFilme();
						break;
					
					case 2:
						Locadora.removeFilme();
						break;
					
					case 3:
						Locadora.pesquisaFilme();
						break;
					
					case 4:
						Locadora.af.imprimirFilmesDisponiveis();
						break;
						
					case 5:
						Locadora.af.imprimirFilmesLocados();
						break;
					
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
			//MENU CLIENTE	
			case 2:
				do {
					opc2 = MenuCliente.menu();
					switch (opc2) {
				
					case 1:
						Locadora.cadastraCliente();
						break;
					
					case 2:
						Locadora.removeCliente();
						break;
					
					case 3:
						Locadora.pesquisaCliente();
						break;
						
					case 4:
						Locadora.ac.imprimirClientesSemPendencia();
						break;
						
					case 5:
						Locadora.ac.imprimirClientesPendentes();
						break;
					
					case 6:
						Locadora.ac.imprimirClientes();
						break;
					
					case 7:
						break;
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 7);
				break;
			//MENU LOCACAO	
			case 3:
				do {
					opc2 = MenuLocacao.menu();
					switch (opc2) {
	
					case 1:
						Locadora.realizarLocacao();
						break;
					
					case 2:
						Locadora.deletarLocacao();
						break;
					
					case 3:
						Locadora.pesquisaLocacao();
						break;
					
					case 4:
						Locadora.al.imprimirLocacaosPendentes();
						break;
					
					case 5:
						Locadora.al.imprimirLocacaosSemPendencia();
						break;
						
					case 6:
						Locadora.al.imprimirLocacaos();
						break;
						
						
					case 7:
						Locadora.al.imprimirLocacaosDevolvidas();
						break;
					
					case 8:
						break;
						
					default:
						System.out.println("Opção Invalida!");
						break;
						
					}
				}while(opc2 != 8);
				break;
			//MENU DEVOLUCAO/PAGAMENTO
			case 4:
				do {
					opc2 = MenuPagamento.menu();
					switch (opc2) {
					
					case 1:
						Locadora.devoluçao();
						break;
					
					case 2:
						Locadora.realizaPagamento();
						break;
					//PESQUISA PAGAMENTO
					case 3:
						Locadora.pesquisaPagamento();
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
