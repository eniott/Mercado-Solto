package br.iesb.mobile.mercado_solto.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.iesb.mobile.mercado_solto.R
import br.iesb.mobile.mercado_solto.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val loginvm: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

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

            loginvm.login(email, password){ retorno->
                Toast.makeText(this, retorno[1], Toast.LENGTH_SHORT).show()
                if (retorno[0]=="Ok"){
                    val ir_para_home = Intent(this, MapsActivity::class.java)
                    startActivity(ir_para_home)
                }
                else{

                }
            }


        }
    }
}
