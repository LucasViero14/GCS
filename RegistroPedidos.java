import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroPedidos {

	private ArrayList<Pedido> pedidos = new ArrayList<>();

	public void adicionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void excluirPedido(Pedido pedido) {
		pedidos.remove(pedido);
	}

	// Pedidos cuja data esta entre inicio e fim (inclusive)
	public ArrayList<Pedido> listarEntreDatas(LocalDate inicio, LocalDate fim) {
		ArrayList<Pedido> resultado = new ArrayList<>();
		for (Pedido p : pedidos) {
			LocalDate data = p.getDataPedido();
			if (!data.isBefore(inicio) && !data.isAfter(fim)) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	public ArrayList<Pedido> buscarPorFuncionario(Funcionario funcionario) {
		ArrayList<Pedido> resultado = new ArrayList<>();
		for (Pedido p : pedidos) {
			if (p.getFuncionarioSol().equals(funcionario)) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	// Pedidos que tenham algum item cuja descricao contenha o termo
	public ArrayList<Pedido> buscarPorDescricao(String descricao) {
		ArrayList<Pedido> resultado = new ArrayList<>();
		String termo = descricao.toLowerCase();
		for (Pedido p : pedidos) {
			for (ItemPedido item : p.getItens()) {
				if (item.getDescricao() != null && item.getDescricao().toLowerCase().contains(termo)) {
					resultado.add(p);
					break;
				}
			}
		}
		return resultado;
	}

	public Pedido buscarMaiorPedidoAberto() {
		return null;
	}

	public int quantidadeTotal() {
		return 0;
	}

	public int quantidadeAprovados() {
		return 0;
	}

	public int quantidadeReprovados() {
		return 0;
	}

	public double percentualAprovados() {
		return 0;
	}

	public double percentualReprovados() {
		return 0;
	}

	public int quantUlt30Dias() {
		return 0;
	}

	public double valorMedioUlt30Dias() {
		return 0;
	}

}
