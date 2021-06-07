package entities;

public class Filme {
		protected String nome;
		protected boolean disponivel;
		//CONSTRUTOR PARA CADASTRO DE CLIENTE
		public Filme(String nome) {
			this.nome = nome;
			this.disponivel = true;
		}
		//CONSTRUTOR PARA PASSAR DO ARQUIVO PARA LISTA
		public Filme(String nome, boolean disponivel) {
			this.nome = nome;
			this.disponivel = disponivel;
		}

		public String getNome() {
			return nome;
		}

		public boolean getDisponivel() {
			return disponivel;
		}

		public void setDisponivel(boolean disponivel) {
			this.disponivel = disponivel;
		}

		@Override
		public String toString() {
			return "Filme [nome=" + nome + ", disponivel=" + disponivel + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Filme other = (Filme) obj;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			return true;
		}


		
}
