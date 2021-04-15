package br.iesb.mobile.meuapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.mobile.meuapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btRegister.setOnClickListener {
            val email = etEmailSignUp.text.toString()
            val password = etPasswordSignUp.text.toString()
            val retype = etRetypeSignUp.text.toString()//

            if (password != retype) {
                Toast.makeText(this, "Olha, digita a msm coisa", Toast.LENGTH_LONG).show()
                return
            }
        }
    }
}