package servico;

import modelo.*;

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
			if (p.getStatus() == StatusPedido.ABERTO.ordinal()
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
		return contarStatus(StatusPedido.APROVADO, StatusPedido.CONCLUIDO);
	}

	public int quantidadeReprovados() {
		return contarStatus(StatusPedido.REPROVADO);
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

	// Pedido.status guarda o indice (ordinal) de StatusPedido.
	// Um pedido concluido foi aprovado antes, entao conta como aprovado.
	private int contarStatus(StatusPedido... status) {
		int contador = 0;
		for (Pedido p : pedidos) {
			for (StatusPedido s : status) {
				if (p.getStatus() == s.ordinal()) {
					contador++;
					break;
				}
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
