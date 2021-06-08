package entities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcervoPagamentos extends Arquivo{
	//CLASSE FORMATA DATA
	private SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
	protected List<Pagamento> pagamentos;
	//CONSTRUTOR QUE INICIA LISTA E CLASSE PAI ARQUIVO, E CARREGA OS DADOS DO ARQUIVO PARA LISTA
	public AcervoPagamentos(String path) {
		super(path);
		this.pagamentos = new ArrayList<Pagamento>();
		carregarLista();
	}
	//FUNCAO QUE PEGA OS DADOS DO ARQUIVO E INSERE NA LISTA 
	public void carregarLista() {
		pagamentos.clear();
		lerArquivo();
		try {
			line = br.readLine();
			while(line !=null) {
				
				String[] line2 = line.split(";");
				Pagamento pagamento = new Pagamento(Double.parseDouble(line2[0]),line2[1],Double.parseDouble(line2[2]),Boolean.parseBoolean(line2[3]),Integer.parseInt(line2[4]));
				pagamentos.add(pagamento);
				
				line = br.readLine();			
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	
	}
	//CADASTRA PAGAMENTO NO FIM DO ARQUIVO
	public void cadastrarPagamento(Pagamento pagamento) {
		gravarArquivo(false);
		try {
			bw.write(pagamento.getValorTotal() + ";" + "00/00/00" + ";" + Double.valueOf(pagamento.getMulta()) + ";" + Boolean.valueOf(pagamento.isPago()) + ";" + Integer.valueOf(pagamento.getCodLocacao()));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		carregarLista();
		System.out.println("Pagamento gerado.");
	}
	//FUNCAO ATUALIZA VALOR TOTAL DE FILME QUE FOI DEVOLVIDO DE ACORDO COM A MULTA(PODE SER APLICADA OU NAO)
	public void atualizarValorTotal(Integer cod, Double VlrMultaPorDia,Integer totalDiasMulta, Double valor) {
		boolean bool = false;
		//ATUALIZA NA LISTA 
		if(totalDiasMulta < 0) {
			for(int i = 0; i<pagamentos.size(); i ++) {
				if(pagamentos.get(i).getCodLocacao() == cod) {
					pagamentos.get(i).setMulta(totalDiasMulta*VlrMultaPorDia*(-1));
					pagamentos.get(i).setValorTotal(valor + pagamentos.get(i).getMulta());
					System.out.println(pagamentos.get(i));
					bool = true;
					
	
				}
			}
		}else {
			System.out.println("Valor continua o mesmo, pois filme foi entregue antes do prazo expirar.");
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Pagamento x : pagamentos) {
			
					bw.write(x.getValorTotal() + ";" + sdf1.format(x.getDataPagamento()) + ";" + Double.valueOf(x.getMulta()) + ";" + Boolean.valueOf(x.isPago()) + ";" + Integer.valueOf(x.getCodLocacao()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Pagamento não encontrado.");
		}
		
	}
	//EXCLUI PAGAMENTO 
	public void excluirPagamento(Integer cod) {
		boolean bool = false;
		//EXCLUI PAGAMENTO DA LISTA 
		for(int i = 0; i<pagamentos.size(); i ++) {
			if(pagamentos.get(i).getCodLocacao() == cod) {
				pagamentos.remove(i);
				bool = true;
			}
		}
		//REGRAVA O ARQUIVO INTEIRO COM O CONTEUDO DA LISTA
		if(bool) {
			gravarArquivo(true);
			try {
				for(Pagamento x : pagamentos) {
			
					bw.write(x.getValorTotal() + ";" + sdf1.format(x.getDataPagamento()) + ";" + Double.valueOf(x.getMulta()) + ";" + Boolean.valueOf(x.isPago()) + ";" + Integer.valueOf(x.getCodLocacao()));
					bw.newLine();
						
				}
				bw.close();
				carregarLista();
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}else {
			System.out.println("Pagamento não encontrado.");
		}
		
	}
	//IMPRIME TODOS PAGAMENTOS DA LISTA
	public void imprimirPagamentos() {
		for(Pagamento pagamento : pagamentos){
			System.out.println(pagamento);
			
		}
	}
	//IMPRIME TODOS PAGAMENTOS PENDENTES
	public void imprimirPagamentosPendentes() {
		for(Pagamento pagamento : pagamentos){
			if(!pagamento.isPago())
			System.out.println(pagamento);
			
		}
	}
	//IMPRIME TODOS PAGAMENTOS PAGOS
	public void imprimirPagamentosSemPendencia() {
		for(Pagamento pagamento : pagamentos){
			if(pagamento.isPago())
			System.out.println(pagamento);
			
		}
	}
	//PESQUISA PAGAMENTO
	public void pesquisarPagamento(Integer cod) {
		for(Pagamento pagamento : pagamentos){
			if(pagamento.getCodLocacao() == cod)
				System.out.println(pagamentos);
			
		}
	}
	//REALIZA O PAGAMENTO
	public void fecharPagamento(Integer cod) {
		boolean bool = false;
		for(int i = 0; i<pagamentos.size(); i ++) {
			if(pagamentos.get(i).getCodLocacao() == cod) {
				pagamentos.get(i).setPago(true);
				pagamentos.get(i).setDataPagamento(new Date());
				System.out.println("Pagamento Realizado");
				bool = true;
			}
		}
		if(!bool)
		System.out.println("Pagamento não encontrado");
	}
}
