package com.example.diceroller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    private var currentPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val btnOpenCamera = findViewById<Button>(R.id.btnOpenCamera)
        btnOpenCamera.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        Log.d("CameraActivity", "Opening camera...")
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        Log.d("CameraActivity", takePictureIntent.toString())
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
                null
            }

            photoFile?.let {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "com.example.diceroller.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }else {
            Log.e("CameraActivity", "No camera app found.")
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageView = findViewById<ImageView>(R.id.imageView)
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            imageView.setImageBitmap(imageBitmap)
        }
    }
}
