package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcervoClientes extends Arquivo {
	private List<Cliente> clientes;
	//CONSTRUTOR QUE INICIA LISTA E CLASSE PAI ARQUIVO, E CARREGA OS DADOS DO ARQUIVO PARA LISTA
	public AcervoClientes(String path) {
		super(path);
		this.clientes = new ArrayList<Cliente>();
		carregarLista();
	}
	//FUNCAO QUE PEGA OS DADOS DO ARQUIVO E INSERE NA LISTA 
	public void carregarLista() {
		clientes.clear();
		lerArquivo();
		try {
			line = br.readLine();
			while(line !=null) {
				
				String[] line2 = line.split(";");
				Cliente cliente = new Cliente(line2[0],Boolean.parseBoolean(line2[1]));
				clientes.add(cliente);
				
				line = br.readLine();			
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	
	}
	//CADASTRA CLIENTE NO FIM DO ARQUIVO
	public void cadastrarCliente(Cliente cliente) {
		gravarArquivo(false);
		try {
			bw.write(cliente.getNome() + ";" + Boolean.toString(cliente.getLocacao()));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		carregarLista();
		System.out.println("Cliente cadastrado.");
	}
	//FUNCAO ATUALIZA ESTADO DO CLIENTE(SE POSSUI LOCACAO OU NAO)
	public void atualizarLocacao(Cliente cliente, boolean locacao) {
		boolean bool = false;
		//ATUALIZA NA LISTA
		for(int i = 0; i<clientes.size(); i ++) {
			
				if(clientes.get(i).getNome().equalsIgnoreCase(cliente.getNome()) ) {
					clientes.get(i).setLocacao(locacao);
					bool = true;
				}
			
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Cliente x : clientes) {
			
					bw.write(x.getNome() + ";" + Boolean.toString(x.getLocacao()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Cliente não encontrado.");
		}
		
	}
	//EXCLUI CLIENTE 
	public void excluirCliente(Cliente cliente) {
		boolean bool = false;
		//EXCLUI CLIENTE DA LISTA 
		for(int i = 0; i<clientes.size(); i ++) {
			if(clientes.get(i).getNome().equalsIgnoreCase(cliente.getNome()) ) {
				clientes.remove(i);
				bool = true;
			}
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Cliente x : clientes) {
			
					bw.write(x.getNome() + ";" + Boolean.toString(x.getLocacao()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
				System.out.println("Cliente Deletado!");
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Cliente não encontrado.");
		}
		
	}
	//IMPRIME TODOS CLIENTES DA LISTA
	public void imprimirClientes() {
		for(Cliente cliente : clientes){
			System.out.println(cliente);
			
		}
	}
	//IMPRIME TODOS CLIENTES COM LOCACAO EM ATIVA
	public void imprimirClientesPendentes() {
		for(Cliente cliente : clientes){
			if(cliente.getLocacao() == true)
			System.out.println(cliente);
			
		}
	}
	//IMPRIME TODOS CLIENTES SEM LOCACAO ATIVA
	public void imprimirClientesSemPendencia() {
		for(Cliente cliente : clientes){
			if(cliente.getLocacao() == false)
			System.out.println(cliente);
			
		}
	}
	//PESQUISA CLIENTE PELO NOME
	public Cliente pesquisarCliente(String nome) {
		for(int i = 0; i<clientes.size(); i ++) {
			if(clientes.get(i).getNome().equalsIgnoreCase(nome)) {
				System.out.println(clientes.get(i));
				return clientes.get(i);	
			}
		}
		
		return null;
	}
}
