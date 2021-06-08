package entities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcervoLocacao extends Arquivo{
	//CLASSE FORMATA DATA
	private SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
	protected List<Locacao> locacoes;
	//ATRIBUTO PARA MANIPULAR DIFERENÇA DE DATAS
	private Long dt;
	//CONSTRUTOR QUE INICIA LISTA E CLASSE PAI ARQUIVO, E CARREGA OS DADOS DO ARQUIVO PARA LISTA
	public AcervoLocacao(String path) {
		super(path);
		this.locacoes = new ArrayList<Locacao>();
		carregarLista();
	}
	//FUNCAO QUE PEGA OS DADOS DO ARQUIVO E INSERE NA LISTA 
	public void carregarLista() {
		locacoes.clear();
		lerArquivo();
		try {
			line = br.readLine();
			while(line !=null) {
				
				String[] line2 = line.split(";");
				Locacao locacao = new Locacao(Integer.parseInt(line2[0]),line2[1],line2[2],line2[3],line2[4],line2[5],Double.parseDouble(line2[6]));
				locacoes.add(locacao);
				
				line = br.readLine();			
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	
	}
	//CADASTRA LOCACAO NO FIM DO ARQUIVO
	public void cadastrarLocacao(Locacao locacao) {
		gravarArquivo(false);
		try {
			bw.write(locacao.getCod() + ";" + locacao.getFilme() + ";" + locacao.getCliente() + ";" + sdf1.format(locacao.getDataLocacao())+ ";" + sdf1.format(locacao.getDataDevolucao())+ ";" + "00/00/00" + ";" + String.valueOf(locacao.getValor()));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		carregarLista();
		System.out.println("Locação cadastrada.");
	}
	//FUNCAO ATUALIZA ESTADO DO LOCACAO PARA FILME QUE FOI DEVOLVIDO
	public void atualizarDataDevolvido(Integer cod) {
		boolean bool = false;
		//ATUALIZA NA LISTA
		for(int i = 0; i<locacoes.size(); i ++) {
			if(locacoes.get(i).getCod() == cod) {
				locacoes.get(i).setDataDevolvido(new Date());
				bool = true;

			}
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Locacao x : locacoes) {
			
					bw.write(Integer.valueOf(x.getCod()) + ";" + x.getFilme() + ";" + x.getCliente() + ";" + sdf1.format(x.getDataLocacao())+ ";" + sdf1.format(x.getDataDevolucao())+ ";" + sdf1.format(x.getDataDevolvido())+ ";" + String.valueOf(x.getValor()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Locação não encontrada.");
		}
		
	}
	//EXCLUI LOCACAO 
	public void excluirLocacao(Integer cod) {
		boolean bool = false;
		//EXCLUI LOCACAO DA LISTA 
		for(int i = 0; i<locacoes.size(); i ++) {
			if(locacoes.get(i).getCod() == cod) {
				locacoes.remove(i);
				bool = true;
			}
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Locacao x : locacoes) {
			
					bw.write(Integer.valueOf(x.getCod()) + ";" + x.getFilme() + ";" + x.getCliente() + ";" + sdf1.format(x.getDataLocacao())+ ";" + sdf1.format(x.getDataDevolucao())+ ";" + sdf1.format(x.getDataDevolvido())+ ";" + String.valueOf(x.getValor()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
				System.out.println("Locação Deletada!");
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Locação não encontrada.");
		}
		
	}
	//IMPRIME TODAS LOCACOES DA LISTA
	public void imprimirLocacaos() {
		for(Locacao locacao : locacoes){
			System.out.println(locacao);
			
		}
	}
	//IMPRIME TODAS LOCACOES QUE PASSARAM DA DATA DE SEREM DEVOLVIDAS
	public void imprimirLocacaosPendentes() {
		for(Locacao locacao : locacoes){
			if(locacao.getDataDevolucao().before(new Date()))
			System.out.println(locacao);
			
		}
	}
	//IMPRIME TODAS LOCACOES QUE ESTAO DENTRO DO PRAZO PARA SEREM DEVOLVIDAS
	public void imprimirLocacaosSemPendencia() {
		for(Locacao locacao : locacoes){
			if(locacao.getDataDevolucao().after(new Date()))
			System.out.println(locacao);
			
		}
	}
	//PESQUISA LOCACAO PELO COD
	public Locacao pesquisarLocacao(Integer cod) {
		for(int i = 0; i<locacoes.size(); i++){
			if(locacoes.get(i).getCod() == cod) {
				System.out.println(locacoes.get(i));
				return locacoes.get(i);
			}
		}
		return null;
	}
	//PESQUISA SE CLIENTE POSSUI MAIS DE UMA LOCACAO
	public boolean clientePossuiMaisDeUmaLocacao(Cliente cliente) {
		int count = 0;
		for(int i = 0; i<locacoes.size(); i++){
			if(locacoes.get(i).getCliente().equalsIgnoreCase(cliente.getNome())) {
				count++;
			}
		}
		if(count > 1) {
			return true;
		}
		return false;
	}
	//CALCULA QUANTIDADE DE DIAS DE ATRASO DA DEVOLUCAO
	public Integer totalDiasMulta(Integer cod) {
		for(int i = 0; i<locacoes.size(); i++){
			if(locacoes.get(i).getCod() == cod) {
				//TRANSFORMA OS DIAS EM MILISSECUNDOS DESDE CERTA DATA DEFINIDA PELO METODO GET TIME E FAZ A DIFERENCA ENTRE AS DATAS E DEPOIS DIVIDE PARA TRANSFORMAR MILISSEGUNDOS EM DIAS
				dt = (locacoes.get(i).getDataDevolucao().getTime() - locacoes.get(i).getDataDevolvido().getTime())/86400000L;
				return dt.intValue();
			}	
		}
		return null;
	}
}
