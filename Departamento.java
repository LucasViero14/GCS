public class Departamento {

	private String nome;
	private double vmax;

	public Departamento(String nome, double vmax) {
		if (nome == null || nome.trim().isEmpty()) {

		}

		}
		this.nome = nome;
		this.vmax = vmax;
	}

	public boolean podeRealizarPedido(double valor) {
		return valor <= vmax;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getVmax() {
		return vmax;
	}

	public void setVmax(double vmax) {
		this.vmax = vmax;
	}

	@Override
	public String toString() {
		return "Departamento{nome='" + nome + "', vmax=" + vmax + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Departamento)) return false;
		Departamento outro = (Departamento) obj;
		return nome != null && nome.equals(outro.nome);
	}


}