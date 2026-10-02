import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private int id;
    private StatusPedido statusPedido;
    private Funcionario funcionarioSol;
    private Departamento departamento;
    private LocalDate dataPedido;
    private LocalDate dataConclusao;
    private ArrayList<ItemPedido> itens;

    public Pedido(int id, Funcionario funcionarioSol, Departamento departamento) {
        this.id = id;
        this.funcionarioSol = funcionarioSol;
        this.departamento = departamento;
        this.dataPedido = LocalDate.now();
        this.dataConclusao = null;
        this.statusPedido = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        if (item != null) {
            itens.add(item);
        }
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemPedido item : itens) {
            total += item.calcularTotal();
        }

        return total;
    }

    public boolean aprovar() {
        if (statusPedido == StatusPedido.ABERTO) {
            statusPedido = StatusPedido.APROVADO;
            return true;
        }

        return false;
    }

    public boolean reprovar() {
        if (statusPedido == StatusPedido.ABERTO) {
            statusPedido = StatusPedido.REPROVADO;
            return true;
        }

        return false;
    }

    public boolean concluir() {
        if (statusPedido == StatusPedido.APROVADO) {
            statusPedido = StatusPedido.CONCLUIDO;
            dataConclusao = LocalDate.now();
            return true;
        }

        return false;
    }

    public int getId() {
        return id;
    }

    public Funcionario getFuncionarioSol() {
        return funcionarioSol;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Pedido " + id +
                " | Funcionário: " + funcionarioSol.getNome() +
                " | Departamento: " + departamento.getNome() +
                " | Data: " + dataPedido +
                " | Status: " + statusPedido +
                " | Total: " + calcularTotal();
    }
}