package br.iesb.mobile.mercado_solto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.mercado_solto.data_class.Estabelecimento
import br.iesb.mobile.mercado_solto.interactor.EstabelecimentosInterector
import com.google.android.gms.maps.model.LatLng

class EstabelecimentosViewModel(app: Application) : AndroidViewModel(app) {
    val interactor = EstabelecimentosInterector(app.applicationContext)
    val estabelecimentos = MutableLiveData<MutableList<Estabelecimento>>()

    fun pegar_estabelecimento(tipo: String) {
        interactor.pegar_estabelecimento(tipo) { estab ->
            estabelecimentos.value = estab
        }
    }

    fun getAddress(latLng: LatLng, callback: (result: String) -> Unit) {
        interactor.getAddress(latLng) { addresses ->
            if (addresses.isNotEmpty()) {
                val addressText = addresses[0].getAddressLine(0)
                callback(addressText)
            }
        }
    }
}