package modelo;

// Status do pedido (enunciado 5.5). Pedido.status guarda o indice (ordinal) deste enum.
public enum StatusPedido {

	ABERTO,

	APROVADO,

	REPROVADO,

	// Pedido aprovado cujos itens ja foram entregues (dataConclusao preenchida)
	CONCLUIDO

}
