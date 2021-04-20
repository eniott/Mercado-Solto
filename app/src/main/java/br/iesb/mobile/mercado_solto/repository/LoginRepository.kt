package br.iesb.mobile.mercado_solto.repository

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginRepository(private val context: Context) {
    private val auth = FirebaseAuth.getInstance()
    fun login(email: String, password: String, callback: (resultado: String) -> Unit) {

        val task_login = auth.signInWithEmailAndPassword(email, password)

        task_login.addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {
                callback("Ok")
                //
            } else {
                callback("Erro")
                //Toast.makeText(this, "E-mail ou senha incorretos.", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun forgot(email: String, callback: (resultado: String) -> Unit){
       val task_login = auth.sendPasswordResetEmail(email)
        task_login.addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {
                callback("Ok")
                //
            } else {
                callback("Erro")
                //Toast.makeText(this, "E-mail ou senha incorretos.", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun singup(email: String, password: String, callback: (resultado: String) -> Unit){
        val auth = FirebaseAuth.getInstance()
        val taskcadastro = auth.createUserWithEmailAndPassword(email, password)
        taskcadastro.addOnCompleteListener { resultado ->
            if (resultado.isSuccessful){
                callback("Ok")
            } else {
                callback("Erro no cadastro")
            }
        }
    }

}