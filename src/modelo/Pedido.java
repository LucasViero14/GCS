package modelo;

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
        this(id, funcionarioSol, departamento, LocalDate.now());
    }

    // Usado nos dados iniciais, para ter pedidos com datas variadas
    public Pedido(int id, Funcionario funcionarioSol, Departamento departamento, LocalDate dataPedido) {
        this.id = id;
        this.funcionarioSol = funcionarioSol;
        this.departamento = departamento;
        this.dataPedido = dataPedido;
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
                " | Funcionario: " + funcionarioSol.getNome() +
                " | Departamento: " + departamento.getNome() +
                " | Data: " + dataPedido +
                " | Status: " + statusPedido +
                " | Total: " + calcularTotal();
    }

    // Detalhes completos do pedido, usados pelo administrador antes de avaliar (5.8.4)
    public String detalhar() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== PEDIDO ").append(id).append(" =====\n");
        sb.append("Solicitante: ").append(funcionarioSol.getNome())
                .append(" (").append(funcionarioSol.getIniciais()).append(")\n");
        sb.append("Departamento: ").append(departamento.getNome())
                .append(String.format(" | Limite por pedido: R$ %.2f%n", departamento.getVmax()));
        sb.append("Data do pedido: ").append(dataPedido).append("\n");
        sb.append("Data de conclusao: ")
                .append(dataConclusao == null ? "-" : dataConclusao).append("\n");
        sb.append("Status: ").append(statusPedido).append("\n");
        sb.append("Itens:\n");

        for (ItemPedido item : itens) {
            sb.append(String.format("  - %s | R$ %.2f x %d = R$ %.2f%n",
                    item.getDescricao(), item.getValorunit(),
                    item.getQuant(), item.calcularTotal()));
        }

        sb.append(String.format("Valor total: R$ %.2f", calcularTotal()));

        return sb.toString();
    }
}