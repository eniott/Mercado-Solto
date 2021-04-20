package br.iesb.mobile.mercado_solto.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.iesb.mobile.mercado_solto.R
import br.iesb.mobile.mercado_solto.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {
    private val loginvm: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        btSendForgot.setOnClickListener {
            val email = etEmailForgot.text.toString()
            loginvm.forgot(email){  resultado ->
                Toast.makeText(this, resultado[1], Toast.LENGTH_LONG).show()
                if(resultado[0]=="Ok"){
                    finish()
                }
            }
        }
        btBackForgot.setOnClickListener {
            finish()
        }
    }
}