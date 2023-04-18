package com.example.taller2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.taller2.MainActivity.Companion.REQUEST_READ_CONTACTS
import com.example.taller2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        private const val REQUEST_READ_CONTACTS = 1
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }
    private fun setListeners(){
        binding.imageImagebutton.setOnClickListener {
            val int = Intent(baseContext, ImageUploadActivity::class.java)
            startActivity(int)
        }
        binding.mapsImagebutton.setOnClickListener {
            val int = Intent(baseContext, MapActivity::class.java)
            startActivity(int)
        }
        binding.contactsImagebutton.setOnClickListener{
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                //No tiene el permiso, pedirlo
                //ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_CONTACTS)
            }else{
                //Ya tiene el permiso, llamo la actividad
                val int = Intent(baseContext, ContactsActivity::class.java)
                startActivity(int)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_READ_CONTACTS -> {
                //If request is cancelled, the result arrays are empty
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //Permiso concedido
                    val int = Intent(baseContext, ContactsActivity::class.java)
                    startActivity(int)
                }else{
                    //Permiso denegado
                    //Funciones limitadas
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }



}