public class Funcionario extends Usuario {
	private Departamento departamento;
	private Pedido[] pedido;

	public Funcionario(Departamento departamento, Pedido[] pedido, String nome, String cpf, String iniciais, int id){
		super(nome,cpf,iniciais,id);
		this.departamento = departamento;
		this.pedido = pedido;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}

	public Pedido[] getPedido(){
		return pedido;
	}

	public void setPedido(Pedido[] pedido){
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "Funcionário{" +
				"departamento=" + departamento +
				", pedido=" + pedido +
				'}';
	}
}
