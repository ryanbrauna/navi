package br.com.navi.mobile.components.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.navi.mobile.R
import br.com.navi.mobile.components.comprador.MainComprador
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_maps_navigation.*

class MapsNavigationActivity : AppCompatActivity() {

    private lateinit var mapView : MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, getString(R.string.access_token))
        setContentView(R.layout.activity_maps_navigation)
        mapView = mapsView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(Style.MAPBOX_STREETS)
        }

        bt_voltar.setOnClickListener {
            startActivity(Intent(this, MainComprador::class.java))
        }

    }

    public override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    public override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

}