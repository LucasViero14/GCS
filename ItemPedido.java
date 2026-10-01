public class ItemPedido {

	private String descricao;
	private double valorunit;
	private int quant;

	public ItemPedido(String descricao, double valorunit, int quant) {

		if (descricao != null && !descricao.trim().isEmpty()) {
			this.descricao = descricao;
		} else {
			System.out.println("Descricao de item invalida, usando padrao 'Sem descricao'");
			this.descricao = "Sem descricao";
		}

		if (valorunit > 0) {
			this.valorunit = valorunit;
		} else {
			System.out.println("Valor unitario invalido, usando padrao 0");
			this.valorunit = 0;
		}

		if (quant > 0) {
			this.quant = quant;
		} else {
			System.out.println("Quantidade invalida, usando padrao 1");
			this.quant = 1;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao != null && !descricao.trim().isEmpty()) {
			this.descricao = descricao;
		} else {
			System.out.println("Tentativa de definir descricao invalida para o item, mudanca ignorada");
		}
	}

	public double getValorunit() {
		return valorunit;
	}

	public void setValorunit(double valorunit) {
		if (valorunit > 0) {
			this.valorunit = valorunit;
		} else {
			System.out.println("Tentativa de definir valor unitario invalido para o item, mudanca ignorada");
		}
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		if (quant > 0) {
			this.quant = quant;
		} else {
			System.out.println("Tentativa de definir quantidade invalida para o item, mudanca ignorada");
		}
	}


	public double calcularTotal() {
		return valorunit * quant;
	}

	public String toString() {
		return "Item: " + descricao + " | Valor unit: " + valorunit +
				" | Quantidade: " + quant + " | Total: " + calcularTotal();
	}
}