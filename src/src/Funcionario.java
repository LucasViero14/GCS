public class Funcionario extends Usuario {
	private Departamento departamento;
	private Usuario usuario;
	private Pedido[] pedido;


	public Departamento getDepartamento() {
		return departamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
}
