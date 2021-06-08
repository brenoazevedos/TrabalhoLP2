package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCliente {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.printf("\n\n1-Cadastrar Cliente"
					+ "\n2-Deletar Cliente"
					+ "\n3-Pesquisar Cliente"
					+ "\n4-Ver Lista de Clientes sem Pendencia"
					+ "\n5-Ver Lista de Clientes com pendencia"
					+ "\n6-Ver Lista de todos os Clientes"
					+ "\n7-Voltar"
					+ "\nDigite a opção desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 10;
	}
}
