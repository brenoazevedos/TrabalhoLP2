package menu;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
			
		try {
			System.out.printf("\n\n1-Filme"
					+ "\n2-Clientes"
					+ "\n3-Loca��o"
					+ "\n4-Pagamentos/Devolu��o"
					+ "\n5-Sair"
					+ "\nDigite a op��o desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
		
	}
}
