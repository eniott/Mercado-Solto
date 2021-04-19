package br.iesb.mobile.mercado_solto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSignUpMain.setOnClickListener {
            val chamasignup = Intent(this, SignUpActivity::class.java)
            startActivity(chamasignup)
        }

        tvForgotMain.setOnClickListener {
            val chamaforgot = Intent(this, ForgotActivity::class.java)
            startActivity(chamaforgot)
        }

        btLoginMain.setOnClickListener {
            val email = etEmailMain.text.toString()
            val password = etPasswordMain.text.toString()

            val auth = FirebaseAuth.getInstance()

            val task_login = auth.signInWithEmailAndPassword(email, password)

            task_login.addOnCompleteListener { resultado ->
                if (resultado.isSuccessful){
                    val ir_para_home = Intent(this, Home::class.java)
                    startActivity(ir_para_home)
                } else {
                    Toast.makeText(this, "E-mail ou senha incorretos.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
