package br.iesb.mobile.mercado_solto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.iesb.mobile.mercado_solto.interactor.LoginInteractor

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val loginin = LoginInteractor(app.applicationContext)
    fun login(email: String, password: String, callback: (resultado: Array<String>) -> Unit) {
        loginin.login(email, password) { retorno ->
            if (retorno == "Email Vazio") {
                callback(arrayOf("Erro", "Digite um Email válido"))
            }
            else if (retorno == "Senha Vazia") {
                callback(arrayOf("Erro", "Digite uma Senha Válida"))
            }
            else if (retorno == "Erro"){
                callback(arrayOf("Erro", "Erro no Login. Verifique seus dados."))
            }
            else{
                callback(arrayOf("Ok", "Login efetuado com sucesso"))
            }
        }
    }
    fun forgot(email: String, callback: (resultado: Array<String>) -> Unit){
        loginin.forgot(email) { retorno ->
            if (retorno == "Email Vazio") {
                callback(arrayOf("Erro", "Digite um Email válido"))
            }
            else if (retorno == "Erro"){
                callback(arrayOf("Erro", "Erro no Login. Verifique seus dados."))
            }
            else{
                callback(arrayOf("Ok", "Email enviado"))
            }

        }
    }
    fun signup(email: String, password: String, confirmpassword:String, callback: (resultado: Array<String>) -> Unit){
        loginin.signup(email, password, confirmpassword) { retorno ->
            if (retorno == "Email Vazio") {
                callback(arrayOf("Erro", "Digite um Email válido"))
            }
            else if (retorno == "Senha Vazia"){
                callback(arrayOf("Erro", "Digite uma Senha Válida"))
            }
            else if (retorno == "Confirmar Senha Vazia"){
                callback(arrayOf("Erro", "Digite a confirmação de senha"))
            }
            else if (retorno == "Senhas Diferentes"){
                callback(arrayOf("Erro", "Senhas não correspondem"))
            }
            else{
                callback(arrayOf("Ok", "Cadastrado com Sucesso!"))
            }

        }
    }
}