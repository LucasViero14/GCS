public class ItemPedido {

	private String descricao;
	private double valorunit;
	private int quant;

	public ItemPedido(String descricao, double valorunit, int quant) {
		this.descricao = descricao;
		this.valorunit = valorunit;
		this.quant = quant;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorunit() {
		return valorunit;
	}

	public void setValorunit(double valorunit) {
		this.valorunit = valorunit;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	// Total do item = valor unitario x quantidade (enunciado 5.6)
	public double calcularTotal() {
		return valorunit * quant;
	}

	public String toString() {
		return "Item: " + descricao + " | Valor unit: " + valorunit +
				" | Quantidade: " + quant + " | Total: " + calcularTotal();
	}
}
