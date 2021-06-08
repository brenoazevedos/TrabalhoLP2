package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLocacao {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.printf("\n\n1-Cadastrar Loca��o"
					+ "\n2-Deletar Loca��o"
					+ "\n3-Pesquisar Loca��o"
					+ "\n4-Ver Lista de clientes em atraso"
					+ "\n5-Ver Lista de Loca��o em dia"
					+ "\n6-Ver Lista de todos as loca��es"
					+ "\n7-Ver Lista de Loca��es devolvidas"
					+ "\n8-Voltar"
					+ "\nDigite a op��o desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
	}
}
