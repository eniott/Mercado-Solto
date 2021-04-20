package br.iesb.mobile.mercado_solto.interactor

import android.content.Context
import android.widget.Toast
import br.iesb.mobile.mercado_solto.repository.LoginRepository

class LoginInteractor(private val context: Context) {
    private val loginr = LoginRepository(context)
    fun login(email:String, password:String, callback:(resultado: String)->Unit) {
        if(email.isEmpty()){
            callback("Email Vazio")
        }
        else if(password.isEmpty()){
            callback("Senha Vazia")
        }
        else{
            loginr.login(email, password, callback)
        }
    }

    fun forgot(email: String, callback: (resultado: String) -> Unit){
        if(email.isEmpty()){
            callback("Email Vazio")
        }
        else {
            loginr.forgot(email, callback)
        }
    }
    fun signup(email: String, password: String, confirmpassword:String, callback: (resultado:String) -> Unit){
        //verificar senhas iguais
        if(email.isEmpty()){
            callback("Email Vazio")
        }
        else if(password.isEmpty()){
            callback("Senha Vazia")
        }
        else if(confirmpassword.isEmpty()){
            callback("Confirmar Senha Vazia")
        }
        else if (password != confirmpassword){
            callback("Senhas Diferentes")
        }
        else{
            loginr.singup(email, password, callback)
            }
        }
}


