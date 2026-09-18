public class Pedido {
	private int id;
	private Funcionario funcionarioSol;
	private Departamento departamento;
	private LocalDate dataPedido;
	private LocalDate dataConclusao;
	private int status;
	private ArrayList<ItemPedido> itens;
	private ItemPedido[] itemPedido;
	private Funcionario funcionario;
	private RegistroPedidos[] registroPedidos;

	public void adicionarItem(ItemPedido item) {
		itens.add(item);
	}

	public void removerItem(ItemPedido item) {
		itens.remove(item);
	}

	public double calcularTotal(ItemPedido[] itemPedido){
		double calculo = 0;
		for(int i = 0; i<itemPedido.length;i++){
			calculo += itemPedido[i].getValor;
		}
		return calculo;
	}

	public void aprovar() {

	}

	public void reprovar() {

	}

	public void concluir() {

	}

}
