package br.iesb.mobile.mercado_solto.data_class

data class DialogflowRequest(
    val text: String,
    val email: String,
    val sessionId: String
)
