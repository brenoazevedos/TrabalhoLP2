package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Locacao {
		//INIALIZA CLASSE QUE FORMATA DATA
		private static SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		private String filme;
		private String cliente;
		private Date dataLocacao;
		private Date dataDevolucao;
		private Date dataDevolvido;
		private Double valor;
		private Integer cod;
		
		//CONSTRUTOR PARA CADASTRO DE LOCACAO
		public Locacao(Integer cod, String filme, String cliente, String dataDevolucao, Double valor) {
			this.cod = cod;
			this.filme = filme;
			this.cliente = cliente;
			this.dataLocacao = new Date();
			this.valor = valor;
			try {
				this.dataDevolucao = sdf1.parse(dataDevolucao);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		//CONSTRUTOR PARA PASSAR DO ARQUIVO PARA LISTA
		public Locacao(Integer cod, String filme, String cliente,String dataLocacao, String dataDevolucao,String dataDevolvido, Double valor) {
			this.cod = cod;
			this.filme = filme;
			this.cliente = cliente;
			this.valor = valor;
			this.dataLocacao = new Date();
			try {
				this.dataLocacao = sdf1.parse(dataLocacao);
				this.dataDevolucao = sdf1.parse(dataDevolucao);
				this.dataDevolvido = sdf1.parse(dataDevolvido);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}

		
		public Integer getCod() {
			return cod;
		}
		public Double getValor() {
			return valor;
		}
		
		public Date getDataDevolucao() {
			return dataDevolucao;
		}


		public void setDataDevolucao(Date dataDevolucao) {
			this.dataDevolucao = dataDevolucao;
		}


		public Date getDataDevolvido() {
			return dataDevolvido;
		}


		public void setDataDevolvido(Date dataDevolvido) {
			this.dataDevolvido = dataDevolvido;
		}


		public String getFilme() {
			return filme;
		}


		public String getCliente() {
			return cliente;
		}


		public Date getDataLocacao() {
			return dataLocacao;
		}
		@Override
		public String toString() {
			//STRING PARA QUANDO FILME FOI DEVOLVIDO E POSSUI DATA REAL DE DEVOLVIDO
			if(dataLocacao.before(dataDevolvido)) {
				return "Locacao [cliente=" + cliente + ", filme=" + filme + ", dataLocacao="
						+ sdf1.format(dataLocacao) + ", dataDevolucao=" + sdf1.format(dataDevolucao) + ", dataDevolvido=" + sdf1.format(dataDevolvido) + ", valor="
						+ valor + ", cod=" + cod + "]";
			}
			//STRING PARA QUANDO FILME NAO FOI DEVOLVIDO E NAO POSSUI DATA REAL DE DEVOLVIDO
			return "Locacao [cliente=" + cliente + ", filme=" + filme + ", dataLocacao="
			+ sdf1.format(dataLocacao) + ", dataDevolucao=" + sdf1.format(dataDevolucao) + ", dataDevolvido= Não foi devolvido" + ", valor="
			+ valor + ", cod=" + cod + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
			result = prime * result + ((cod == null) ? 0 : cod.hashCode());
			result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
			result = prime * result + ((dataDevolvido == null) ? 0 : dataDevolvido.hashCode());
			result = prime * result + ((dataLocacao == null) ? 0 : dataLocacao.hashCode());
			result = prime * result + ((filme == null) ? 0 : filme.hashCode());
			result = prime * result + ((valor == null) ? 0 : valor.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Locacao other = (Locacao) obj;
			if (cliente == null) {
				if (other.cliente != null)
					return false;
			} else if (!cliente.equals(other.cliente))
				return false;
			if (cod == null) {
				if (other.cod != null)
					return false;
			} else if (!cod.equals(other.cod))
				return false;
			if (dataDevolucao == null) {
				if (other.dataDevolucao != null)
					return false;
			} else if (!dataDevolucao.equals(other.dataDevolucao))
				return false;
			if (dataDevolvido == null) {
				if (other.dataDevolvido != null)
					return false;
			} else if (!dataDevolvido.equals(other.dataDevolvido))
				return false;
			if (dataLocacao == null) {
				if (other.dataLocacao != null)
					return false;
			} else if (!dataLocacao.equals(other.dataLocacao))
				return false;
			if (filme == null) {
				if (other.filme != null)
					return false;
			} else if (!filme.equals(other.filme))
				return false;
			if (valor == null) {
				if (other.valor != null)
					return false;
			} else if (!valor.equals(other.valor))
				return false;
			return true;
		}

		
		
		
		
		
		
		
		
		
}
