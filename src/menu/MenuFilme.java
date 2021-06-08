package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuFilme {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.printf("\n\n1-Cadastrar Filme"
					+ "\n2-Deletar Filme"
					+ "\n3-Pesquisar Filme"
					+ "\n4-Ver Lista de Filmes Disponiveis"
					+ "\n5-Ver Lista de Filmes Locados"
					+ "\n6-Ver Lista de todos os Filmes"
					+ "\n7-Voltar"
					+ "\nDigite a op��o desejada: ");
			return sc.nextInt();
		} catch(InputMismatchException e) {
				System.out.println("Error: " + e.getMessage());
		}
		return 10;
	}
}
