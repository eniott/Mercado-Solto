package br.iesb.mobile.mercado_solto.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.iesb.mobile.mercado_solto.R
import br.iesb.mobile.mercado_solto.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val loginvm: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
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
        loginvm.signup(email, password, confirm_password){ retorno ->
            Toast.makeText(this, retorno[1], Toast.LENGTH_SHORT).show()
            if (retorno[0]=="Ok"){
                val ir_para_home = Intent(this, MapsActivity::class.java)
                startActivity(ir_para_home)
            }
        }
    }
}