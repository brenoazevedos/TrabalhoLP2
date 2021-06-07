package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pagamento {
		//INIALIZA CLASSE QUE FORMATA DATA
		private static SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		private Double valorTotal;
		private Date dataPagamento;
		private Double multa;
		private boolean pago;
		private Integer codLocacao;
		
		//CONSTRUTOR PARA CADASTRO DE PAGAMENTO
		public Pagamento(Double valorTotal, Integer codLocacao) {
			this.valorTotal = valorTotal;
			this.multa = 0.0;
			this.pago = false;
			this.codLocacao = codLocacao;
		}
		
		//CONSTRUTOR PARA PARA PASSAR DO ARQUIVO PARA LISTA
		public Pagamento(Double valorTotal, String dataPagamento, Double multa, boolean pago, Integer codLocacao) {
			this.valorTotal = valorTotal;
			this.multa = multa;
			this.pago = pago;
			this.codLocacao = codLocacao;
			try {
				this.dataPagamento = sdf1.parse(dataPagamento);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}


		public Double getValorTotal() {
			return valorTotal;
		}


		public void setValorTotal(Double valorTotal) {
			this.valorTotal = valorTotal;
		}


		public Date getDataPagamento() {
			return dataPagamento;
		}


		public void setDataPagamento(Date dataPagamento) {
			this.dataPagamento = dataPagamento;
		}


		public Double getMulta() {
			return multa;
		}


		public void setMulta(Double multa) {
			this.multa = multa;
		}


		public boolean isPago() {
			return pago;
		}


		public void setPago(boolean pago) {
			this.pago = pago;
		}


		public Integer getCodLocacao() {
			return codLocacao;
		}


		@Override
		public String toString() {
			//STRING PARA QUANDO JA FOI PAGO E POSSUI DATA REAL DE PAGAMENTO
			if(pago) {
				return "Pagamento [codLocacao=" + codLocacao + ", ValorSemMulta=" + (valorTotal-multa)  + ", multa=" + multa + ", valorTotal=" + valorTotal + ", dataPagamento=" + sdf1.format(dataPagamento)
					+ ", pago=" + pago + "]";
			}
			//STRING PARA QUANDO NAO POSSUI DATA DE PAGAMENTO REAL
			return "Pagamento [codLocacao=" + codLocacao + ", ValorSemMulta=" + (valorTotal-multa)  + ", multa=" + multa + ", valorTotal=" + valorTotal + ", dataPagamento= Não foi feito pagamento" +
			", pago=" + pago + "]";
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codLocacao == null) ? 0 : codLocacao.hashCode());
			result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
			result = prime * result + ((multa == null) ? 0 : multa.hashCode());
			result = prime * result + (pago ? 1231 : 1237);
			result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
			Pagamento other = (Pagamento) obj;
			if (codLocacao == null) {
				if (other.codLocacao != null)
					return false;
			} else if (!codLocacao.equals(other.codLocacao))
				return false;
			if (dataPagamento == null) {
				if (other.dataPagamento != null)
					return false;
			} else if (!dataPagamento.equals(other.dataPagamento))
				return false;
			if (multa == null) {
				if (other.multa != null)
					return false;
			} else if (!multa.equals(other.multa))
				return false;
			if (pago != other.pago)
				return false;
			if (valorTotal == null) {
				if (other.valorTotal != null)
					return false;
			} else if (!valorTotal.equals(other.valorTotal))
				return false;
			return true;
		}
		
		
		
		
		
}
