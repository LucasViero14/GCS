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

	@Override
	public String toString() {
		return "Funcionário{" +
				"departamento=" + departamento +
				", pedido=" + pedido +
				'}';
	}
}
