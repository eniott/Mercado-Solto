package br.iesb.mobile.mercado_solto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        btSendForgot.setOnClickListener {
            val email = etEmailForgot.text.toString()
            val auth = FirebaseAuth.getInstance()
            if(TextUtils.isEmpty(email)){

                Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email)
            Toast.makeText(this, "Siga as instruções no seu e-mail para resetar a senha.", Toast.LENGTH_LONG).show()
            finish()
        }
        btBackForgot.setOnClickListener {
            finish()
        }
    }
}