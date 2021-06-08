package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLocacao {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.printf("\n\n1-Cadastrar Locação"
					+ "\n2-Deletar Locação"
					+ "\n3-Pesquisar Locação"
					+ "\n4-Ver Lista de clientes em atraso"
					+ "\n5-Ver Lista de Locação em dia"
					+ "\n6-Ver Lista de todos as locações"
					+ "\n7-Ver Lista de Locações devolvidas"
					+ "\n8-Voltar"
					+ "\nDigite a opção desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
	}
}
