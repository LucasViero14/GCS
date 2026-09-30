import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private int id;

	private Funcionario funcionarioSol;

	private Departamento departamento;

	private LocalDate dataPedido;

	private LocalDate dataConclusao;

	private int status;

	private ArrayList<ItemPedido> itens;

	public void adicionarItem(ItemPedido item) {

	}

	public void removerItem(ItemPedido item) {

	}

	public double calcularTotal() {
		return 0;
	}

	public void aprovar() {

	}

	public void reprovar() {

	}

	public void concluir() {

	}

}
