package app;

import modelo.Administrador;
import modelo.Departamento;
import modelo.Funcionario;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.StatusPedido;
import modelo.Usuario;
import servico.RegistroPedidos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private Usuario usuarioLogado;

    private ArrayList<Usuario> usuarios;
    private ArrayList<Departamento> departamentos;
    private ArrayList<Funcionario> funcionarios;
    private RegistroPedidos registroPedidos;

    private Scanner scanner;

    private int proximoIdPedido = 1;

    public App() {
        usuarios = new ArrayList<>();
        departamentos = new ArrayList<>();
        funcionarios = new ArrayList<>();
        registroPedidos = new RegistroPedidos();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App app = new App();

        app.inicializarDados();
        app.executar();
    }

    public void executar() {
        System.out.println("=================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO");
        System.out.println("=================================");

        selecionarUsuario();

        while (usuarioLogado != null) {
            if (usuarioLogado instanceof Administrador) {
                menuAdministrador();
            } else if (usuarioLogado instanceof Funcionario) {
                menuFuncionario();
            } else {
                System.out.println("Tipo de usuario nao reconhecido.");
                break;
            }
        }

        scanner.close();
        System.out.println("Sistema encerrado.");
    }

    public void selecionarUsuario() {

        System.out.println("\n===== SELECIONAR USUARIO =====");

        for (Usuario usuario : usuarios) {
            String tipo;

            if (usuario instanceof Administrador) {
                tipo = "Administrador";
            } else if (usuario instanceof Funcionario) {
                tipo = "Funcionario";
            } else {
                tipo = "Usuario";
            }

            System.out.println(
                    usuario.getId() + " - " +
                    usuario.getNome() + " (" +
                    usuario.getIniciais() + ") - " +
                    tipo
            );
        }

        System.out.println("0 - Sair");

        int id = lerInteiro("Escolha o ID do usuario: ");

        if (id == 0) {
            usuarioLogado = null;
            return;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarioLogado = usuario;

                System.out.println("Usuario atual: " + descreverUsuarioLogado());

                return;
            }
        }

        System.out.println("Usuario nao encontrado.");
    }

    private String descreverUsuarioLogado() {
        return usuarioLogado.getNome() + " (" + usuarioLogado.getIniciais() + ")";
    }

    private void menuFuncionario() {

        boolean continuar = true;

        while (continuar && usuarioLogado instanceof Funcionario) {

            System.out.println("\n===== MENU FUNCIONARIO =====");
            System.out.println("Usuario atual: " + descreverUsuarioLogado());
            System.out.println("1 - Criar pedido");
            System.out.println("2 - Excluir pedido em aberto");
            System.out.println("3 - Trocar usuario");
            System.out.println("0 - Sair");

            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    criarPedido();
                    break;

                case 2:
                    excluirPedido();
                    break;

                case 3:
                    selecionarUsuario();
                    continuar = usuarioLogado != null;
                    break;

                case 0:
                    usuarioLogado = null;
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void menuAdministrador() {

        boolean continuar = true;

        while (continuar && usuarioLogado instanceof Administrador) {

            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("Usuario atual: " + descreverUsuarioLogado());
            System.out.println("1 - Criar pedido");
            System.out.println("2 - Listar pedidos entre datas");
            System.out.println("3 - Buscar pedidos por funcionario");
            System.out.println("4 - Buscar pedidos por descricao");
            System.out.println("5 - Avaliar pedido");
            System.out.println("6 - Concluir pedido");
            System.out.println("7 - Estatisticas");
            System.out.println("8 - Trocar usuario");
            System.out.println("0 - Sair");

            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    criarPedido();
                    break;

                case 2:
                    listarPedidosEntreDatas();
                    break;

                case 3:
                    buscarPedidosPorFuncionario();
                    break;

                case 4:
                    buscarPedidosPorDescricao();
                    break;

                case 5:
                    avaliarPedido();
                    break;

                case 6:
                    concluirPedido();
                    break;

                case 7:
                    mostrarEstatisticas();
                    break;

                case 8:
                    selecionarUsuario();
                    continuar = usuarioLogado != null;
                    break;

                case 0:
                    usuarioLogado = null;
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(
                        scanner.nextLine().replace(",", ".")
                );
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor valido.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
	private void inicializarDados() {

    // =========================
    // DEPARTAMENTOS
    // =========================

    Departamento ti = new Departamento("Tecnologia da Informacao", 5000.00);
    Departamento financeiro = new Departamento("Financeiro", 10000.00);
    Departamento recursosHumanos = new Departamento("Recursos Humanos", 3000.00);
    Departamento compras = new Departamento("Compras", 8000.00);
    Departamento marketing = new Departamento("Marketing", 6000.00);

    departamentos.add(ti);
    departamentos.add(financeiro);
    departamentos.add(recursosHumanos);
    departamentos.add(compras);
    departamentos.add(marketing);

    // =========================
    // FUNCIONARIOS
    // =========================

    Funcionario funcionario1 = new Funcionario(
            ti, new Pedido[0],
            "Lucas Silva", "111.111.111-01", "LS", 1
    );

    Funcionario funcionario2 = new Funcionario(
            ti, new Pedido[0],
            "Ana Costa", "111.111.111-02", "AC", 2
    );

    Funcionario funcionario3 = new Funcionario(
            ti, new Pedido[0],
            "Pedro Santos", "111.111.111-03", "PS", 3
    );

    Funcionario funcionario4 = new Funcionario(
            financeiro, new Pedido[0],
            "Mariana Souza", "111.111.111-04", "MS", 4
    );

    Funcionario funcionario5 = new Funcionario(
            financeiro, new Pedido[0],
            "Rafael Lima", "111.111.111-05", "RL", 5
    );

    Funcionario funcionario6 = new Funcionario(
            financeiro, new Pedido[0],
            "Beatriz Alves", "111.111.111-06", "BA", 6
    );

    Funcionario funcionario7 = new Funcionario(
            recursosHumanos, new Pedido[0],
            "Gabriel Rocha", "111.111.111-07", "GR", 7
    );

    Funcionario funcionario8 = new Funcionario(
            recursosHumanos, new Pedido[0],
            "Julia Martins", "111.111.111-08", "JM", 8
    );

    Funcionario funcionario9 = new Funcionario(
            recursosHumanos, new Pedido[0],
            "Felipe Oliveira", "111.111.111-09", "FO", 9
    );

    Funcionario funcionario10 = new Funcionario(
            compras, new Pedido[0],
            "Eduardo Pereira", "111.111.111-10", "EP", 10
    );

    Funcionario funcionario11 = new Funcionario(
            compras, new Pedido[0],
            "Camila Mendes", "111.111.111-11", "CM", 11
    );

    Funcionario funcionario12 = new Funcionario(
            compras, new Pedido[0],
            "Bruno Ferreira", "111.111.111-12", "BF", 12
    );

    Funcionario funcionario13 = new Funcionario(
            marketing, new Pedido[0],
            "Joao Carvalho", "111.111.111-13", "JC", 13
    );

    Funcionario funcionario14 = new Funcionario(
            marketing, new Pedido[0],
            "Larissa Gomes", "111.111.111-14", "LG", 14
    );

    Funcionario funcionario15 = new Funcionario(
            marketing, new Pedido[0],
            "Thiago Ribeiro", "111.111.111-15", "TR", 15
    );

    funcionarios.add(funcionario1);
    funcionarios.add(funcionario2);
    funcionarios.add(funcionario3);
    funcionarios.add(funcionario4);
    funcionarios.add(funcionario5);
    funcionarios.add(funcionario6);
    funcionarios.add(funcionario7);
    funcionarios.add(funcionario8);
    funcionarios.add(funcionario9);
    funcionarios.add(funcionario10);
    funcionarios.add(funcionario11);
    funcionarios.add(funcionario12);
    funcionarios.add(funcionario13);
    funcionarios.add(funcionario14);
    funcionarios.add(funcionario15);

    // Todos os funcionarios tambem sao usuarios do sistema
    usuarios.add(funcionario1);
    usuarios.add(funcionario2);
    usuarios.add(funcionario3);
    usuarios.add(funcionario4);
    usuarios.add(funcionario5);
    usuarios.add(funcionario6);
    usuarios.add(funcionario7);
    usuarios.add(funcionario8);
    usuarios.add(funcionario9);
    usuarios.add(funcionario10);
    usuarios.add(funcionario11);
    usuarios.add(funcionario12);
    usuarios.add(funcionario13);
    usuarios.add(funcionario14);
    usuarios.add(funcionario15);

    // =========================
    // ADMINISTRADORES
    // =========================

    Administrador administrador1 = new Administrador(
            "Carlos Almeida", "222.222.222-01", "CA", 16, 1001
    );

    Administrador administrador2 = new Administrador(
            "Fernanda Dias", "222.222.222-02", "FD", 17, 1002
    );

    usuarios.add(administrador1);
    usuarios.add(administrador2);

    // =========================
    // PEDIDOS
    // =========================
    // Datas relativas a hoje, para sempre haver pedidos dentro e fora
    // dos ultimos 30 dias, e todos os status representados.

    LocalDate hoje = LocalDate.now();

    Pedido pedido1 = criarPedidoInicial(funcionario1, hoje.minusDays(90),
            new ItemPedido("Notebook Dell", 3500.00, 1),
            new ItemPedido("Mouse sem fio", 80.00, 2));
    pedido1.aprovar();
    pedido1.concluir();

    Pedido pedido2 = criarPedidoInicial(funcionario2, hoje.minusDays(60),
            new ItemPedido("Monitor 24 polegadas", 900.00, 3));
    pedido2.reprovar();

    Pedido pedido3 = criarPedidoInicial(funcionario4, hoje.minusDays(45),
            new ItemPedido("Licenca de software contabil", 4500.00, 1),
            new ItemPedido("Calculadora financeira", 250.00, 4));
    pedido3.aprovar();

    Pedido pedido4 = criarPedidoInicial(funcionario7, hoje.minusDays(25),
            new ItemPedido("Cadeira ergonomica", 1200.00, 2));
    pedido4.aprovar();

    Pedido pedido5 = criarPedidoInicial(funcionario10, hoje.minusDays(20),
            new ItemPedido("Resma de papel A4", 30.00, 50),
            new ItemPedido("Caneta esferografica", 2.50, 200));
    pedido5.reprovar();

    Pedido pedido6 = criarPedidoInicial(funcionario13, hoje.minusDays(15),
            new ItemPedido("Banner promocional", 350.00, 5),
            new ItemPedido("Camiseta personalizada", 45.00, 40));
    pedido6.aprovar();
    pedido6.concluir();

    criarPedidoInicial(funcionario5, hoje.minusDays(10),
            new ItemPedido("Impressora multifuncional", 2200.00, 2),
            new ItemPedido("Toner", 400.00, 6));

    criarPedidoInicial(funcionario3, hoje.minusDays(5),
            new ItemPedido("SSD 1TB", 450.00, 4),
            new ItemPedido("Memoria RAM 16GB", 300.00, 4));

    criarPedidoInicial(funcionario11, hoje.minusDays(3),
            new ItemPedido("Notebook Lenovo", 4200.00, 1));

    criarPedidoInicial(funcionario8, hoje.minusDays(1),
            new ItemPedido("Livro de gestao de pessoas", 90.00, 5));

    criarPedidoInicial(funcionario14, hoje,
            new ItemPedido("Camera fotografica", 2800.00, 1),
            new ItemPedido("Tripe", 200.00, 1));
	}

	// Monta um pedido dos dados iniciais e ja registra no sistema
	private Pedido criarPedidoInicial(Funcionario funcionario, LocalDate data, ItemPedido... itens) {

	    Pedido pedido = new Pedido(
	            proximoIdPedido,
	            funcionario,
	            funcionario.getDepartamento(),
	            data
	    );

	    for (ItemPedido item : itens) {
	        pedido.adicionarItem(item);
	    }

	    registroPedidos.adicionarPedido(pedido);
	    proximoIdPedido++;

	    return pedido;
	}
private void criarPedido() {

    Funcionario funcionarioSolicitante;

    // Se quem está usando o sistema é funcionário,
    // ele mesmo é o solicitante do pedido.
    if (usuarioLogado instanceof Funcionario) {

        funcionarioSolicitante = (Funcionario) usuarioLogado;

    } else {

        // Se for administrador, ele escolhe o funcionário
        // para quem o pedido será registrado.
        System.out.println("\n===== SELECIONAR FUNCIONARIO =====");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                    funcionario.getId() + " - " +
                    funcionario.getNome() + " - " +
                    funcionario.getDepartamento().getNome()
            );
        }

        int idFuncionario = lerInteiro("ID do funcionario solicitante: ");

        funcionarioSolicitante = null;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == idFuncionario) {
                funcionarioSolicitante = funcionario;
                break;
            }
        }

        if (funcionarioSolicitante == null) {
            System.out.println("Funcionario nao encontrado.");
            return;
        }
    }

    Departamento departamento = funcionarioSolicitante.getDepartamento();

    Pedido pedido = new Pedido(
            proximoIdPedido,
            funcionarioSolicitante,
            departamento
    );

    System.out.println("\n===== NOVO PEDIDO =====");
    System.out.println("Solicitante: " + funcionarioSolicitante.getNome());
    System.out.println("Departamento: " + departamento.getNome());
    System.out.printf("Valor maximo: R$ %.2f%n", departamento.getVmax());

    boolean adicionouItem = false;

    while (true) {

        System.out.println("\n--- Novo item ---");

        String descricao = lerTexto("Descricao: ");
        double valorUnitario = lerDouble("Valor unitario: R$ ");
        int quantidade = lerInteiro("Quantidade: ");

        if (valorUnitario <= 0 || quantidade <= 0) {
            System.out.println("Item nao adicionado. Valor e quantidade devem ser maiores que zero.");
        } else {

            ItemPedido item = new ItemPedido(
                    descricao,
                    valorUnitario,
                    quantidade
            );

            double novoTotal = pedido.calcularTotal() + item.calcularTotal();

            if (!departamento.podeRealizarPedido(novoTotal)) {
                System.out.printf(
                        "Item nao adicionado. O total de R$ %.2f ultrapassaria " +
                        "o limite de R$ %.2f do departamento.%n",
                        novoTotal,
                        departamento.getVmax()
                );
            } else {
                pedido.adicionarItem(item);
                adicionouItem = true;

                System.out.printf(
                        "Item adicionado. Total atual: R$ %.2f%n",
                        pedido.calcularTotal()
                );
            }
        }

        // Pergunta sempre, para o usuario poder desistir mesmo depois de um item recusado
        String continuar = lerTexto("Adicionar outro item? (S/N): ");

        if (!continuar.equalsIgnoreCase("S")) {
            break;
        }
    }

    if (!adicionouItem) {
        System.out.println("Pedido cancelado: nenhum item foi adicionado.");
        return;
    }

    registroPedidos.adicionarPedido(pedido);
    proximoIdPedido++;

    System.out.println("\nPedido criado com sucesso!");
    System.out.println(pedido);
}
private void excluirPedido() {

    if (!(usuarioLogado instanceof Funcionario)) {
        System.out.println("Apenas funcionarios podem excluir pedidos.");
        return;
    }

    Funcionario funcionario = (Funcionario) usuarioLogado;

    ArrayList<Pedido> pedidosDoFuncionario =
            registroPedidos.buscarPorFuncionario(funcionario);

    ArrayList<Pedido> pedidosAbertos = new ArrayList<>();

    for (Pedido pedido : pedidosDoFuncionario) {
        if (pedido.getStatusPedido() == StatusPedido.ABERTO) {
            pedidosAbertos.add(pedido);
        }
    }

    if (pedidosAbertos.isEmpty()) {
        System.out.println("Voce nao possui pedidos abertos para excluir.");
        return;
    }

    System.out.println("\n===== SEUS PEDIDOS ABERTOS =====");

    for (Pedido pedido : pedidosAbertos) {
        System.out.println(pedido);
    }

    int idPedido = lerInteiro("ID do pedido que deseja excluir: ");

    for (Pedido pedido : pedidosAbertos) {

        if (pedido.getId() == idPedido) {
            registroPedidos.excluirPedido(pedido);

            System.out.println("Pedido excluido com sucesso.");
            return;
        }
    }

    System.out.println(
            "Pedido nao encontrado ou voce nao tem permissao para exclui-lo."
    );
	}
	private void listarPedidosEntreDatas() {

    System.out.println("\n===== LISTAR PEDIDOS POR DATA =====");

    LocalDate inicio = lerData("Data inicial (dd/MM/yyyy): ");
    LocalDate fim = lerData("Data final (dd/MM/yyyy): ");

    if (fim.isBefore(inicio)) {
        System.out.println("A data final nao pode ser anterior a data inicial.");
        return;
    }

    ArrayList<Pedido> pedidos =
            registroPedidos.listarEntreDatas(inicio, fim);

    if (pedidos.isEmpty()) {
        System.out.println("Nenhum pedido encontrado nesse periodo.");
        return;
    }

    System.out.println("\nPedidos encontrados:");

    for (Pedido pedido : pedidos) {
        System.out.println(pedido);
    }
}


private void buscarPedidosPorFuncionario() {

    System.out.println("\n===== BUSCAR POR FUNCIONARIO =====");

    for (Funcionario funcionario : funcionarios) {
        System.out.println(
                funcionario.getId() + " - " +
                funcionario.getNome() + " - " +
                funcionario.getDepartamento().getNome()
        );
    }

    int idFuncionario =
            lerInteiro("ID do funcionario: ");

    Funcionario funcionarioEscolhido = null;

    for (Funcionario funcionario : funcionarios) {

        if (funcionario.getId() == idFuncionario) {
            funcionarioEscolhido = funcionario;
            break;
        }
    }

    if (funcionarioEscolhido == null) {
        System.out.println("Funcionario nao encontrado.");
        return;
    }

    ArrayList<Pedido> pedidos =
            registroPedidos.buscarPorFuncionario(funcionarioEscolhido);

    if (pedidos.isEmpty()) {
        System.out.println("Nenhum pedido encontrado para esse funcionario.");
        return;
    }

    System.out.println(
            "\nPedidos de " + funcionarioEscolhido.getNome() + ":"
    );

    for (Pedido pedido : pedidos) {
        System.out.println(pedido);
    }
}


private void buscarPedidosPorDescricao() {

    System.out.println("\n===== BUSCAR POR DESCRICAO =====");

    String descricao =
            lerTexto("Digite a descricao ou parte dela: ");

    if (descricao.trim().isEmpty()) {
        System.out.println("A descricao nao pode ficar vazia.");
        return;
    }

    ArrayList<Pedido> pedidos =
            registroPedidos.buscarPorDescricao(descricao);

    if (pedidos.isEmpty()) {
        System.out.println("Nenhum pedido encontrado.");
        return;
    }

    System.out.println("\nPedidos encontrados:");

    for (Pedido pedido : pedidos) {
        System.out.println(pedido);
    }
}


private void avaliarPedido() {

    if (!(usuarioLogado instanceof Administrador)) {
        System.out.println("Apenas administradores podem avaliar pedidos.");
        return;
    }

    ArrayList<Pedido> pedidos =
            registroPedidos.listarTodos();

    ArrayList<Pedido> pedidosAbertos = new ArrayList<>();

    for (Pedido pedido : pedidos) {

        if (pedido.getStatusPedido() == StatusPedido.ABERTO) {
            pedidosAbertos.add(pedido);
        }
    }

    if (pedidosAbertos.isEmpty()) {
        System.out.println("Nao existem pedidos abertos para avaliar.");
        return;
    }

    System.out.println("\n===== PEDIDOS ABERTOS =====");

    for (Pedido pedido : pedidosAbertos) {
        System.out.println(pedido);
    }

    int idPedido =
            lerInteiro("ID do pedido que deseja avaliar: ");

    Pedido pedidoEscolhido = null;

    for (Pedido pedido : pedidosAbertos) {

        if (pedido.getId() == idPedido) {
            pedidoEscolhido = pedido;
            break;
        }
    }

    if (pedidoEscolhido == null) {
        System.out.println("Pedido nao encontrado.");
        return;
    }

    System.out.println();
    System.out.println(pedidoEscolhido.detalhar());

    System.out.println("\n1 - Aprovar");
    System.out.println("2 - Reprovar");
    System.out.println("0 - Voltar sem avaliar");

    int opcao = lerInteiro("Escolha: ");

    Administrador administrador =
            (Administrador) usuarioLogado;

    boolean resultado;

    if (opcao == 1) {

        resultado =
                administrador.aprovarPedido(pedidoEscolhido);

        if (resultado) {
            System.out.println("Pedido aprovado com sucesso.");
        } else {
            System.out.println("Nao foi possivel aprovar o pedido.");
        }

    } else if (opcao == 2) {

        resultado =
                administrador.reprovarPedido(pedidoEscolhido);

        if (resultado) {
            System.out.println("Pedido reprovado com sucesso.");
        } else {
            System.out.println("Nao foi possivel reprovar o pedido.");
        }

    } else if (opcao == 0) {

        System.out.println("Pedido mantido em aberto.");

    } else {

        System.out.println("Opcao invalida.");
    }
}


private void concluirPedido() {

    if (!(usuarioLogado instanceof Administrador)) {
        System.out.println("Apenas administradores podem concluir pedidos.");
        return;
    }

    ArrayList<Pedido> pedidos =
            registroPedidos.listarTodos();

    ArrayList<Pedido> pedidosAprovados = new ArrayList<>();

    for (Pedido pedido : pedidos) {

        if (pedido.getStatusPedido() == StatusPedido.APROVADO) {
            pedidosAprovados.add(pedido);
        }
    }

    if (pedidosAprovados.isEmpty()) {
        System.out.println("Nao existem pedidos aprovados para concluir.");
        return;
    }

    System.out.println("\n===== PEDIDOS APROVADOS =====");

    for (Pedido pedido : pedidosAprovados) {
        System.out.println(pedido);
    }

    int idPedido =
            lerInteiro("ID do pedido que deseja concluir: ");

    for (Pedido pedido : pedidosAprovados) {

        if (pedido.getId() == idPedido) {

            if (pedido.concluir()) {
                System.out.println("Pedido concluido com sucesso.");
            } else {
                System.out.println("Nao foi possivel concluir o pedido.");
            }

            return;
        }
    }

    System.out.println("Pedido nao encontrado.");
}


private void mostrarEstatisticas() {

    System.out.println("\n===== ESTATISTICAS =====");

    int total =
            registroPedidos.quantidadeTotal();

    int aprovados =
            registroPedidos.quantidadeAprovados();

    int reprovados =
            registroPedidos.quantidadeReprovados();

    double percentualAprovados =
            registroPedidos.percentualAprovados();

    double percentualReprovados =
            registroPedidos.percentualReprovados();

    int ultimos30Dias =
            registroPedidos.quantUlt30Dias();

    double mediaUltimos30Dias =
            registroPedidos.valorMedioUlt30Dias();

    Pedido maiorPedidoAberto =
            registroPedidos.buscarMaiorPedidoAberto();

    System.out.println("Total de pedidos: " + total);

    System.out.println(
            "Pedidos aprovados: " +
            aprovados +
            String.format(" (%.2f%%)", percentualAprovados)
    );

    System.out.println(
            "Pedidos reprovados: " +
            reprovados +
            String.format(" (%.2f%%)", percentualReprovados)
    );

    System.out.println(
            "Pedidos dos ultimos 30 dias: " +
            ultimos30Dias
    );

    System.out.printf(
            "Valor medio dos ultimos 30 dias: R$ %.2f%n",
            mediaUltimos30Dias
    );

    System.out.println("\nMaior pedido ainda aberto:");

    if (maiorPedidoAberto == null) {
        System.out.println("Nenhum pedido aberto.");
    } else {
        System.out.println(maiorPedidoAberto);
    }
}


private LocalDate lerData(String mensagem) {

    DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    while (true) {

        try {

            System.out.print(mensagem);

            return LocalDate.parse(
                    scanner.nextLine(),
                    formato
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Data invalida. Use o formato dd/MM/yyyy."
            );
        }
    }
	}
}