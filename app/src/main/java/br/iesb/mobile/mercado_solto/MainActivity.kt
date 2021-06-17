package br.iesb.mobile.mercado_solto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import br.iesb.mobile.mercado_solto.view.activities.MapsActivity
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

            //Impede o App de crashar caso o campo Email ou senha esteja vazio
            if(TextUtils.isEmpty(email)){

                Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }
            if(TextUtils.isEmpty(password)){

                Toast.makeText(this,"Please enter your password",Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }

            val auth = FirebaseAuth.getInstance()

            val task_login = auth.signInWithEmailAndPassword(email, password)

            task_login.addOnCompleteListener { resultado ->
                if (resultado.isSuccessful){
                    val ir_para_home = Intent(this, MapsActivity::class.java)
                    startActivity(ir_para_home)
                } else {
                    Toast.makeText(this, "E-mail ou senha incorretos.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
