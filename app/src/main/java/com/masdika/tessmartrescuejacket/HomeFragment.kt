package com.masdika.tessmartrescuejacket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masdika.tessmartrescuejacket.databinding.FragmentHomeBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

class HomeFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var locationOverlay: MyLocationNewOverlay

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize osmdroid configuration
        val ctx = requireContext().applicationContext
        Configuration.getInstance()
            .load(ctx, androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx))

        mapView = MapView(activity)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)

        val mapViewContainer = binding.viewPeta
        mapViewContainer.addView(mapView)

        val madiunLocation = GeoPoint(-7.6298, 111.5232) // Koordinat Madiun
        mapView.controller.setCenter(madiunLocation)
        mapView.controller.setZoom(15.0) // Setel tingkat zoom paling dekat

        // Optional: Tambahkan overlay lokasi pengguna
        val locationProvider = GpsMyLocationProvider(requireContext())
        locationOverlay = MyLocationNewOverlay(locationProvider, mapView)
        locationOverlay.enableMyLocation()
        mapView.overlays.add(locationOverlay)

        // Optional: Tambahkan overlay skala
        val scaleBarOverlay = ScaleBarOverlay(mapView)
        mapView.overlays.add(scaleBarOverlay)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val mapCenter = mapView.mapCenter
        val geoPoint = GeoPoint(mapCenter.latitudeE6, mapCenter.longitudeE6)
        outState.putParcelable("mapState", geoPoint)
        outState.putDouble("zoom", mapView.zoomLevelDouble)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDetach()
    }

}