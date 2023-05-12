package com.example.diceroller

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val locationTextView: TextView = findViewById(R.id.locationTextView)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        requestLocationPermission()

        locationTextView.text = "Fetching location..."
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            fetchLocation()
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    val locationText = "Latitude: $latitude\nLongitude: $longitude"
                    val locationTextView: TextView = findViewById(R.id.locationTextView)
                    locationTextView.text = locationText
                } ?: run {
                    val locationTextView: TextView = findViewById(R.id.locationTextView)
                    locationTextView.text = "Location not available"
                }
            }
            .addOnFailureListener { exception ->
                val locationTextView: TextView = findViewById(R.id.locationTextView)
                locationTextView.text = "Failed to fetch location"
                Log.e("LocationActivity", "Failed to fetch location: ${exception.message}", exception)
            }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}
