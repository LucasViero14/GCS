public class Administrador extends Usuario{
private int codAdministrador;
    public Administrador(String nome, String cpf, String iniciais, int id, int codAdministrador){
        super(nome, cpf, iniciais, id);
        this.codAdministrador = codAdministrador;
    }

    public void aprovarPedido(Pedido pedido){
        pedido.setStatus(1);
    }

    public void reprovarPedido(Pedido pedido){
        pedido.setStatus(2);
    }

    public int getCodAdministrador(){
        return codAdministrador;
    }
}