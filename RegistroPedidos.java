import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroPedidos {

	private ArrayList<Pedido> pedidos;

	public void adicionarPedido(Pedido pedido) {

	}

	public void excluirPedido(Pedido pedido) {

	}

	public ArrayList<Pedido> listarEntreDatas(LocalDate inicio, LocalDate fim) {
		return null;
	}

	public ArrayList<Pedido> buscarPorFuncionario(Funcionario funcionario) {
		return null;
	}

	public ArrayList<Pedido> buscarPorDescricao(String descricao) {
		return null;
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
