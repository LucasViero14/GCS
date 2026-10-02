package modelo;

public class Departamento {

	private String Nome;
	private double Vmax;

	public Departamento(String nome, double vmax) {

		if (nome != null && !nome.trim().isEmpty()) {
			this.Nome = nome;
		} else {
			System.out.println("Nome de departamento invalido, usando padrao 'Sem nome'");
			this.Nome = "Sem nome";
		}

		if (vmax > 0) {
			this.Vmax = vmax;
		} else {
			System.out.println("Valor maximo invalido, usando padrao 0");
			this.Vmax = 0;
		}
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		if (nome != null && !nome.trim().isEmpty()) {
			this.Nome = nome;
		} else {
			System.out.println("Tentativa de definir nome invalido para o departamento, mudanca ignorada");
		}
	}

	public double getVmax() {
		return Vmax;
	}

	public void setVmax(double vmax) {
		if (vmax > 0) {
			this.Vmax = vmax;
		} else {
			System.out.println("Tentativa de definir valor maximo invalido para o departamento, mudanca ignorada");
		}
	}


	public boolean podeRealizarPedido(double valor) {
		return valor <= Vmax;
	}

	public String toString() {
		return "Departamento: " + Nome + " | Valor maximo por pedido: " + Vmax;
	}
}