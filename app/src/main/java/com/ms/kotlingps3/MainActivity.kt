@file:Suppress("DEPRECATION")

package com.ms.kotlingps3

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class MainActivity : AppCompatActivity() , LocationListener {



    private  lateinit var locationmanger: LocationManager

    private  var locationpermationgpscod=2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val click: Button =findViewById(R.id.click)

        click.setOnClickListener {

            getloction()

        }







    }

    private fun getloction() {

        //to check permition from user to use gps

        locationmanger=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if((ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),2)
        }

        locationmanger.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,this)

        Toast.makeText(this,"  1 fun getloction()", Toast.LENGTH_SHORT).show()


    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode ==locationpermationgpscod){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this," 2 GPS permission on", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"GPS permission off", Toast.LENGTH_SHORT).show()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)



    }




    override fun onLocationChanged(location: Location) {

        Toast.makeText(this,"3 CITY ", Toast.LENGTH_SHORT).show()
        //to put data in textview
        //to work this code must gps working in mobil

        val latitude:TextView=findViewById(R.id.latitud)
        val longitude:TextView=findViewById(R.id.longitude)
        latitude.text=location.latitude.toString()
        longitude.text=location.longitude.toString()


       val geocoder= Geocoder(this, Locale.getDefault())



        val Adress=geocoder.getFromLocation(location.latitude.toDouble(),location.longitude.toDouble(),1)
        val cityname= Adress?.get(0)?.locality





       longitude.text=location.longitude.toString()+" "+ cityname.toString().trimEnd()

       Toast.makeText(this,cityname.toString(),Toast.LENGTH_SHORT).show()



//        Infromtion from gps
//          country name
//        val cityname2= Adress?.get(0)?.countryName

//           governmaent name
//         val cityname2= Adress?.get(0)?.adminArea
//             city name
//        val cityname2= Adress?.get(0)?.subAdminArea

  //                subcity name
//        val cityname2= Adress?.get(0)?.locality

//


    }

}
