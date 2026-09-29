public class Administrador extends Usuario{
private int codAdministrador;
    public Administrador(int id, int codAdministrador){
        super(id);
        this.codAdministrador = codAdministrador
    }

    public void aprovarPedido(Pedido pedido){
        pedido.setStatus(1);
    }

    public void reprovarPedido(Pedido pedido){
        pedido.setStatus(2);
    }

    public int  getCodAdministrador(){
        return codAdministrador
    }
}