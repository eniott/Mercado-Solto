package br.iesb.mobile.mercado_solto.interactor

import android.content.Context
import android.location.Address
import br.iesb.mobile.mercado_solto.data_class.Estabelecimento
import br.iesb.mobile.mercado_solto.repository.EstabelecimentosRepository
import com.google.android.gms.maps.model.LatLng

class EstabelecimentosInterector(private val context: Context) {
    val repository = EstabelecimentosRepository(context)

    fun pegar_estabelecimento(
        tipo: String,
        callback: (MutableList<Estabelecimento>) -> Unit
    ) {
        repository.pegar_estabelecimento(tipo, callback)
    }

    fun getAddress(latLng: LatLng, callback: (result: List<Address>) -> Unit) {
        repository.getAddress(latLng, callback)
    }
}