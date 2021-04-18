package br.iesb.mobile.mercado_solto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btRegisterSignUp.setOnClickListener {
            cadastrar()
        }

        btBackSignUp.setOnClickListener {
            finish()
        }
    }

    private fun cadastrar() {
        val email = etEmailSignUp.text.toString()
        val password = etPasswordSignUp.text.toString()
        val confirm_password = etConfirmPasswordSignUp.text.toString()

        if (password != confirm_password) {
            Toast.makeText(this, "A senha deve ser igual nos dois campos.", Toast.LENGTH_LONG).show()
            return
        }

        val auth = FirebaseAuth.getInstance()
        val task_cadastro = auth.createUserWithEmailAndPassword(email, password)
        task_cadastro.addOnCompleteListener { resultado ->
            if (resultado.isSuccessful){
                finish()
            } else {
                Toast.makeText(this, "Erro no cadastro.", Toast.LENGTH_LONG).show()
            }
        }
    }
}