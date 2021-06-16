package br.iesb.mobile.mercado_solto.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        val spiner: Spinner = findViewById(R.id.categorias)

        //val categorias = ListOf<String>("teste, teste2, teste3")

        val categorias = ArrayList<String>()
        categorias.add("Mercados")
        categorias.add("Restaurantes")
        categorias.add("Roupas")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner.adapter = arrayAdapter




    }
}