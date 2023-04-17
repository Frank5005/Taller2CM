package com.example.taller2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
//import com.example.taller2.dataBinding.ActivityImageUploadBinding
import com.example.taller2.databinding.ActivityImageUploadBinding

class ImageUploadActivity : AppCompatActivity() {
    private val galleryPermission = registerForActivityResult(ActivityResultContracts.GetContent()){
        it?.let { it1 -> setImage(it1) }
    }
    private val cameraPermission = registerForActivityResult(ActivityResultContracts.TakePicture()){
        if(it){
            setImage(uriCamera)
        }
    }
    private lateinit var uriCamera: Uri

    private lateinit var binding: ActivityImageUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }
    private fun setListeners(){
        binding.selectImageButton.setOnClickListener {
            galleryPermission.launch("image/*")
        }
        binding.cameraButton.setOnClickListener {
            val file = File(filesDir, "picFromCamera")
            uriCamera = FileProvider.getUriForFile(baseContext, applicationContext.packageName+".fileprovider", file)
            cameraPermission.launch(uriCamera)
        }
    }


    private fun setImage(uri: Uri){
        binding.imageImageview.setImageURI(uri)
    }
}