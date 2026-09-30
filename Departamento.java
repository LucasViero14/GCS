public class Departamento {

	private String nome;
	private double vmax;

	public Departamento(String nome, double vmax) {
		this.nome = nome;
		this.vmax = vmax;
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

	// Verifica se o valor do pedido esta dentro do limite do departamento (enunciado 5.7)
	public boolean podeRealizarPedido(double valor) {
		return valor <= vmax;
	}

	public String toString() {
		return "Departamento: " + nome + " | Valor maximo por pedido: " + vmax;
	}
}