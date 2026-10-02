package servico;

import modelo.Funcionario;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.StatusPedido;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroPedidos {

    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    public void excluirPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public ArrayList<Pedido> listarTodos() {
        return new ArrayList<>(pedidos);
    }

    public ArrayList<Pedido> listarEntreDatas(LocalDate inicio, LocalDate fim) {
        ArrayList<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            LocalDate data = pedido.getDataPedido();

            if (!data.isBefore(inicio) && !data.isAfter(fim)) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    public ArrayList<Pedido> buscarPorFuncionario(Funcionario funcionario) {
        ArrayList<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getFuncionarioSol().equals(funcionario)) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    public ArrayList<Pedido> buscarPorDescricao(String descricao) {
        ArrayList<Pedido> resultado = new ArrayList<>();

        String termo = descricao.toLowerCase();

        for (Pedido pedido : pedidos) {
            for (ItemPedido item : pedido.getItens()) {

                if (item.getDescricao() != null &&
                        item.getDescricao().toLowerCase().contains(termo)) {

                    resultado.add(pedido);
                    break;
                }
            }
        }

        return resultado;
    }

    public Pedido buscarMaiorPedidoAberto() {
        Pedido maior = null;

        for (Pedido pedido : pedidos) {
            if (pedido.getStatusPedido() == StatusPedido.ABERTO) {

                if (maior == null ||
                        pedido.calcularTotal() > maior.calcularTotal()) {

                    maior = pedido;
                }
            }
        }

        return maior;
    }

    public int quantidadeTotal() {
        return pedidos.size();
    }

    public int quantidadeAprovados() {
        int quantidade = 0;

        for (Pedido pedido : pedidos) {
            // Pedido concluido tambem foi aprovado antes de ser entregue
            if (pedido.getStatusPedido() == StatusPedido.APROVADO ||
                    pedido.getStatusPedido() == StatusPedido.CONCLUIDO) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public int quantidadeReprovados() {
        int quantidade = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getStatusPedido() == StatusPedido.REPROVADO) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public double percentualAprovados() {
        if (quantidadeTotal() == 0) {
            return 0;
        }

        return (quantidadeAprovados() * 100.0) / quantidadeTotal();
    }

    public double percentualReprovados() {
        if (quantidadeTotal() == 0) {
            return 0;
        }

        return (quantidadeReprovados() * 100.0) / quantidadeTotal();
    }

    public int quantUlt30Dias() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusDays(30);

        int quantidade = 0;

        for (Pedido pedido : pedidos) {
            LocalDate data = pedido.getDataPedido();

            if (!data.isBefore(inicio) && !data.isAfter(hoje)) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public double valorMedioUlt30Dias() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusDays(30);

        double soma = 0;
        int quantidade = 0;

        for (Pedido pedido : pedidos) {
            LocalDate data = pedido.getDataPedido();

            if (!data.isBefore(inicio) && !data.isAfter(hoje)) {
                soma += pedido.calcularTotal();
                quantidade++;
            }
        }

        if (quantidade == 0) {
            return 0;
        }

        return soma / quantidade;
    }
}