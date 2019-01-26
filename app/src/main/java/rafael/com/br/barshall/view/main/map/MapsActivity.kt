package rafael.com.br.barshall.view.main.map

import android.Manifest
import android.app.Fragment
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import rafael.com.br.barshall.R
import rafael.com.br.barshall.utils.Permissoes
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    val permissoesLocalizacao = listOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var locaionManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Permissoes.validaPermissao(permissoesLocalizacao.toTypedArray(), this, 1)


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initLocationListener(){
        locationListener = object :LocationListener{
            override fun onLocationChanged(location: Location?) {
                val minhaPosicao = LatLng(-23.47336,  -46.739898)
                addMarcador(minhaPosicao, "Mâe to no maps")
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minhaPosicao, 17f))
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

            }

            override fun onProviderEnabled(p0: String?) {
            }

            override fun onProviderDisabled(p0: String?) {
            }

        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for(resposta in grantResults){
            if(resposta == PackageManager.PERMISSION_DENIED){
                Toast.makeText(applicationContext, " Sem permissao sem acesso", Toast.LENGTH_LONG).show()
            }else{
                requestLocationUpdates()

            }
        }

    }

    private fun addMarcador(latLng: LatLng, titulo: String) {
        mMap.addMarker(MarkerOptions().position(latLng).title(titulo))
    }

    private fun requestLocationUpdates(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        initLocationListener()
        requestLocationUpdates()

        val fiapPaulista = LatLng(-23.563814, -46.652442)
        val fiapAclimacao = LatLng(-23.571913, -46.623336)
        val fiapVilaOlimpia = LatLng(-23.595060, -46.685333)

      /*  mMap.setOnMapClickListener {

            addMarcador(it, getEnderecoFormatado(it))
        }

        mMap.setOnMapLongClickListener {

            addMarcador(it, getEnderecoFormatado(it))
        }
*/
        mMap.addMarker(MarkerOptions()
                .position(fiapPaulista)
                .title("Fiap Paulista")
                .snippet(getEnderecoFormatado(fiapPaulista))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        mMap.addMarker(MarkerOptions()
                .position(fiapAclimacao)
                .title("Fiap Aclimação")
                .snippet(getEnderecoFormatado(fiapAclimacao))
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.cheers))
        )
        mMap.addMarker(MarkerOptions()
                .position(fiapVilaOlimpia)
                .title("Fiap Vila Olimpia")
                .snippet(getEnderecoFormatado(fiapVilaOlimpia)))

        val circulo = CircleOptions()
        circulo.center(fiapPaulista)
        circulo.radius(200.0)
        circulo.fillColor(Color.argb(128, 0, 51,102))
        circulo.strokeWidth(1f)
        mMap.addCircle(circulo)
    }

    private fun getEnderecoFormatado(latLng: LatLng): String {
        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        val endereco = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

        return "${endereco[0].thoroughfare}, ${endereco[0].subThoroughfare} " +
                "${endereco[0].subLocality}, ${endereco[0].locality} - " +
                "${endereco[0].postalCode}"
    }
}
