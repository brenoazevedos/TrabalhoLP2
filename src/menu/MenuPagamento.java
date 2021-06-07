package menu;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPagamento {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.printf("\n\n1-Devolução"
					+ "\n2-Realizar Pagamento"
					+ "\n3-Pesquisar Pagamento"
					+ "\n4-Ver Lista de Pagamentos feitos"
					+ "\n5-Ver Lista de Pagamentos pendentes"
					+ "\n6-Ver Lista de todos os Pagamentos"
					+ "\nDigite a opção desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
	}
}
