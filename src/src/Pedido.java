import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
	private int id;
	private boolean statusPedido;
	private Funcionario funcionarioSol;
	private Departamento departamento;
	private LocalDate dataPedido;
	private LocalDate dataConclusao;
	private ArrayList<ItemPedido> itens;
	private Funcionario funcionario;

	public Pedido(int id, Funcionario funcionarioSol, Departamento departamento, ArrayList<ItemPedido> itens, Funcionario funcionario) {
		this.id = id;
		this.statusPedido = false;
		this.funcionarioSol = funcionarioSol;
		this.departamento = departamento;
		this.dataPedido = LocalDate.now();
		this.dataConclusao = null;
		this.itens = itens;
		this.funcionario = funcionario;
	}

	public void adicionarItem(Pedido item) {
		itens.add(item);
	}
	public void removerItem(Pedido item) {
		itens.remove(item);
	}
	public double calcularTotal(){
		double calculo = 0;
		for (ItemPedido item : itens) {
			calculo += item.getValor();
		}
		return calculo;
	}
	public void setStatusPedido(boolean val){
		this.statusPedido = val;
	}
	public boolean getStatusPedido(){
		return statusPedido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionario getFuncionarioSol() {
		return funcionarioSol;
	}

	public void setFuncionarioSol(Funcionario funcionarioSol) {
		this.funcionarioSol = funcionarioSol;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public ArrayList<Pedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Pedido> itens) {
		this.itens = itens;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void concluir() {
		setStatusPedido(true);
		dataConclusao = LocalDate.now();
	}

}
