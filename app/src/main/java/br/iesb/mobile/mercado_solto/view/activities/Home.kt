package br.iesb.mobile.mercado_solto.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.iesb.mobile.mercado_solto.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btCategoriasHome.setOnClickListener {
            val chamacategorias = Intent(this, CategoriasActivity::class.java)
            startActivity(chamacategorias)
        }

        btCompletarPerfilHome.setOnClickListener {
            val chamaperfil = Intent(this, PerfilActivity::class.java)
            startActivity(chamaperfil)
        }

    }
}