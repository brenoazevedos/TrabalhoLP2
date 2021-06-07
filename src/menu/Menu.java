package menu;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
			
		try {
			System.out.printf("\n\n1-Filme"
					+ "\n2-Clientes"
					+ "\n3-Locação"
					+ "\n4-Pagamentos/Devolução"
					+ "\n5-Sair"
					+ "\nDigite a opção desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
		
	}
}
