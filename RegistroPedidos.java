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

	// ESTATISTICAS (8.1 a 8.3)

	// Pedido aberto = status ABERTO (enunciado 5.5). Retorna null se nao houver
	public Pedido buscarMaiorPedidoAberto() {
		Pedido maior = null;
		for (Pedido p : pedidos) {
			if (nomeStatus(p).equals("ABERTO")
					&& (maior == null || p.calcularTotal() > maior.calcularTotal())) {
				maior = p;
			}
		}
		return maior;
	}

	public int quantidadeTotal() {
		return pedidos.size();
	}

	public int quantidadeAprovados() {
		return contarPorStatus("APROVADO");
	}

	public int quantidadeReprovados() {
		return contarPorStatus("REPROVADO");
	}

	public double percentualAprovados() {
		return percentual(quantidadeAprovados());
	}

	public double percentualReprovados() {
		return percentual(quantidadeReprovados());
	}

	public int quantUlt30Dias() {
		return pedidosUlt30Dias().size();
	}

	public double valorMedioUlt30Dias() {
		ArrayList<Pedido> recentes = pedidosUlt30Dias();
		if (recentes.isEmpty()) {
			return 0;
		}
		double soma = 0;
		for (Pedido p : recentes) {
			soma += p.calcularTotal();
		}
		return soma / recentes.size();
	}

	// O status do Pedido e um int (indice no enum). Comparar pelo nome do valor
	// permite trocar o enum pela sugestao (com ABERTO) sem alterar esta classe.
	private String nomeStatus(Pedido p) {
		statuspedido[] valores = statuspedido.values();
		int indice = p.getStatus();
		if (indice < 0 || indice >= valores.length) {
			return "";
		}
		return valores[indice].name();
	}

	private int contarPorStatus(String nome) {
		int contador = 0;
		for (Pedido p : pedidos) {
			if (nomeStatus(p).equals(nome)) {
				contador++;
			}
		}
		return contador;
	}

	// Percentual sobre o total de pedidos (0 a 100)
	private double percentual(int quantidade) {
		if (pedidos.isEmpty()) {
			return 0;
		}
		return quantidade * 100.0 / pedidos.size();
	}

	private ArrayList<Pedido> pedidosUlt30Dias() {
		LocalDate hoje = LocalDate.now();
		return listarEntreDatas(hoje.minusDays(30), hoje);
	}

}
