public class Administrador extends Usuario {

    private int codAdministrador;

    public Administrador(String nome, String cpf, String iniciais, int id, int codAdministrador) {
        super(nome, cpf, iniciais, id);
        this.codAdministrador = codAdministrador;
    }

    public boolean aprovarPedido(Pedido pedido) {
        return pedido.aprovar();
    }

    public boolean reprovarPedido(Pedido pedido) {
        return pedido.reprovar();
    }

    public int getCodAdministrador() {
        return codAdministrador;
    }
}