package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcervoFilmes extends Arquivo{
		protected List<Filme> filmes;
		//CONSTRUTOR QUE INICIA LISTA E CLASSE PAI ARQUIVO, E CARREGA OS DADOS DO ARQUIVO PARA LISTA
		public AcervoFilmes(String path) {
			super(path);
			this.filmes = new ArrayList<Filme>();
			carregarLista();
		}
		//FUNCAO QUE PEGA OS DADOS DO ARQUIVO E INSERE NA LISTA 
		public void carregarLista() {
			filmes.clear();
			lerArquivo();
			try {
				line = br.readLine();
				while(line !=null) {
					
					String[] line2 = line.split(";");
					Filme filme = new Filme(line2[0],Boolean.parseBoolean(line2[1]));
					filmes.add(filme);
					
					line = br.readLine();			
				}
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
				
		}
		//CADASTRA CLIENTE NO FIM DO ARQUIVO
		public void cadastrarFilme(Filme filme) {
			gravarArquivo(false);
			try {
				bw.write(filme.getNome() + ";" + Boolean.toString(filme.getDisponivel()));
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			carregarLista();
			System.out.println("Filme cadastrado.");
		}
		//FUNCAO ATUALIZA ESTADO DO fILME(SE ESTÁ DISPONIVEL OU NAO)
		public void atualizarDisponibilidade(Filme filme, boolean disponivel) {
			boolean bool = false;
			//ATUALIZA NA LISTA
			for(int i = 0; i<filmes.size(); i ++) {
				if(filmes.get(i).getNome().equalsIgnoreCase(filme.getNome())) {
					filmes.get(i).setDisponivel(disponivel);
					bool = true;
				}
			}
			//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
			if(bool) {
				gravarArquivo(true);
				try {
					for(Filme x : filmes) {
				
						bw.write(x.getNome() + ";" + Boolean.toString(x.getDisponivel()));
						bw.newLine();
							
					}
					bw.close();
					carregarLista();
				} catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}else {
				System.out.println("Filme não encontrado.");
			}
		}
		//EXCLUI FILME 
		public void excluirFilme(Filme filme) {
			boolean bool = false;
			//EXCLUI FILME DA LISTA
			for(int i = 0; i<filmes.size(); i ++) {
				if(filmes.get(i).getNome().equalsIgnoreCase(filme.getNome())) {
					filmes.remove(i);
					bool = true;
				}
			}
			//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
			if(bool) {
				gravarArquivo(true);
				try {
					for(Filme x : filmes) {
				
						bw.write(x.getNome() + ";" + Boolean.toString(x.getDisponivel()));
						bw.newLine();
							
					}
					bw.close();
					carregarLista();
					System.out.println("Filme Deletado!");
				} catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}else {
				System.out.println("Filme não encontrado.");
			}
		}
		//IMPRIME TODOS FILMES DA LISTA
		public void imprimirFilmes() {
			for(Filme filme : filmes){
				System.out.println(filme);
				
			}
		}
		//IMPRIME TODOS CLIENTES LOCADOS
		public void imprimirFilmesLocados() {
			for(Filme filme : filmes){
				if(filme.getDisponivel() == false)
					System.out.println(filme);
				
			}
		}
		//IMPRIME TODOS CLIENTES DISPONIVEIS
		public void imprimirFilmesDisponiveis() {
			for(Filme filme : filmes){
				if(filme.getDisponivel() == true)
					System.out.println(filme);
				
			}
		}
		//PESQUISA FILME
		public Filme pesquisarFilme(String nome) {
			for(int i = 0; i<filmes.size(); i ++) {
				if(filmes.get(i).getNome().equalsIgnoreCase(nome)) {
					System.out.println(filmes.get(i));
					return filmes.get(i);	
				}
			}
			
			return null;
		}
}
