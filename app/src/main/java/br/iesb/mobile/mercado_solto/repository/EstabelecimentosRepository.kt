package br.iesb.mobile.mercado_solto.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import br.iesb.mobile.mercado_solto.data_class.Estabelecimento
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.IOException

class EstabelecimentosRepository(private val context: Context) {
    private val database = FirebaseDatabase.getInstance()

    fun pegar_estabelecimento(tipo: String, callback: (MutableList<Estabelecimento>) -> Unit) {
        val reference = database.getReference("estabelecimentos/$tipo")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = snapshot.children
                val nova_lista = mutableListOf<Estabelecimento>()

                lista.forEach { estab ->
                    val novo = estab.getValue(Estabelecimento::class.java)

                    if (novo != null) {
                        nova_lista.add(novo)
                    }
                }

                callback(nova_lista)
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }
        })
    }

    fun getAddress(latLng: LatLng, callback: (result: List<Address>) -> Unit) {
        val geocoder = Geocoder(context)
        val addresses: List<Address>

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

            callback(addresses)
        } catch (e: IOException) {
            //
        }
    }
}