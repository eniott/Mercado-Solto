package br.iesb.mobile.mercado_solto.view.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.iesb.mobile.mercado_solto.R
import br.iesb.mobile.mercado_solto.viewmodel.EstabelecimentosViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlin.random.Random

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var lastSelectedMark: Marker? = null
    private val viewModel: EstabelecimentosViewModel by lazy {
        ViewModelProvider(this).get(EstabelecimentosViewModel::class.java)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        btCompletarPerfilHome.setOnClickListener {
            val chamaperfil = Intent(this, PerfilActivity::class.java)
            startActivity(chamaperfil)
        }

        val spiner: Spinner = findViewById(R.id.categorias)

        val categorias = ArrayList<String>()
        categorias.add("Mercados")
        categorias.add("Restaurantes")
        categorias.add("Roupas")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        viewModel.estabelecimentos.observe(this, Observer { estab ->
            mMap.clear()
            estab.forEach { e ->
                e.lat?.let { e.lng?.let { it1 -> LatLng(it, it1) } }
                    ?.let { placeMarkerOnMap(it, Random.nextInt(0, 100)) }
            }
        })

        spiner.adapter = arrayAdapter

        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.pegar_estabelecimento(categorias[position])
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        setUpMap(googleMap)

//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun setUpMap(googleMap: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )

            return
        }

        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14f))
            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng, id: Int?) {
        viewModel.getAddress(location) { titleStr ->
            val markerOptions = MarkerOptions().position(location).title(titleStr)

            mMap.addMarker(markerOptions).tag = id
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        lastSelectedMark = marker
        marker?.showInfoWindow()

        return true
    }
}