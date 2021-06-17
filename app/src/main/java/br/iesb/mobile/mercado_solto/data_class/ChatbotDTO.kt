package br.iesb.mobile.mercado_solto.data_class

data class ChatbotDTO(
    val queryResult: QueryResult? = null
)

data class QueryResult(
    val fulfillmentText: String? = null
)