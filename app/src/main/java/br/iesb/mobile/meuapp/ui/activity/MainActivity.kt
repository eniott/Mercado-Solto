package br.iesb.mobile.meuapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.mobile.meuapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSignUp.setOnClickListener {
            val intencaoDeChamada = Intent(this, SignUpActivity::class.java)
            startActivity(intencaoDeChamada)
        }

        tvForgot.setOnClickListener {
            val intencaoDeChamada = Intent(this, ForgotActivity::class.java)
            startActivity(intencaoDeChamada)
        }

        btLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val auth = FirebaseAuth.getInstance()

            val taskDeLogin = auth.signInWithEmailAndPassword(email, password)

            taskDeLogin.addOnCompleteListener { resultado ->
                if (resultado.isSuccessful) {
                    /// fazer algo com sucesso
                    val intentIrParaTelaHome = Intent(this, HomeActivity::class.java)
                    startActivity(intentIrParaTelaHome)
                } else {
                    Toast.makeText(this, "Olha, deu erro no Login!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}