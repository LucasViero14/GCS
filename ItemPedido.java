public class ItemPedido {

	private String descricao;
	private double valorunit;
	private int quant;

	public ItemPedido(String descricao, double valorunit, int quant) {
		if (descricao == null || descricao.trim().isEmpty()) {
		}
		if (valorunit <= 0) {

		}
		if (quant <= 0) {

		}
		this.descricao = descricao;
		this.valorunit = valorunit;
		this.quant = quant;
	}

	public double calcularTotal() {
		return valorunit * quant;
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


	public int getQuant() {
		return quant;
	}

	@Override
	public String toString() {
		return "ItemPedido{descricao='" + descricao + "', valorunit=" + valorunit +
				", quant=" + quant + ", total=" + calcularTotal() + "}";
	}
}
