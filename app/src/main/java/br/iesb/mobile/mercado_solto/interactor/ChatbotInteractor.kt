package br.iesb.mobile.mercado_solto.interactor

import android.content.Context
import br.iesb.mobile.mercado_solto.data_class.DialogflowRequest
import br.iesb.mobile.mercado_solto.repository.ChatbotRepository

class ChatbotInteractor(context: Context) {
    private val repository = ChatbotRepository(context, "https://mercado-solto.herokuapp.com/")

    fun sendText(request: DialogflowRequest, callback: (String) -> Unit) {
        repository.sendText(request, callback)
    }

    fun verifyEmpty(text: String, callback: (String) -> Unit) {
        if (text.isEmpty()) {
            callback("EMPTY")
        } else {
            callback("OK")
        }
    }
}
