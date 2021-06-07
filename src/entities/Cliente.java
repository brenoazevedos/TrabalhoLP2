package entities;

public class Cliente {
		protected String nome;
		protected boolean locacao;

		
		//CONSTRUTOR DE CLIENTE UTILIZADO PARA CADASTRO
		public Cliente(String nome) {
			this.nome = nome;
			this.locacao = false;
		}
		//CONSTRUTOR DE CLIENTE PARA PASSAR DO ARQUIVO PARA LISTA
		public Cliente(String nome, boolean locacao) {
			this.nome = nome;
			this.locacao = locacao;
		}



		public boolean getLocacao() {
			return locacao;
		}


		public void setLocacao(boolean Locacao) {
			this.locacao = Locacao;
		}


		public String getNome() {
			return nome;
		}


		@Override
		public String toString() {
			return "Cliente [nome=" + nome + ", Locacao=" + locacao + "]";
		}




		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cliente other = (Cliente) obj;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			return true;
		}


		


		
		

		
		
}
