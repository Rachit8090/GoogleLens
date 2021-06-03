package com.example.googlelens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCameraActivity.setOnClickListener {
            startActivity(Intent(this,CameraActivity::class.java))
        }

        btnBarcodeActivity.setOnClickListener {
            startActivity(Intent(this,BarcodeActivity::class.java))
        }
        btnFaceDetectActivity.setOnClickListener {
            startActivity(Intent(this,FaceDetectActivity::class.java))
        }
        btnImageLabelActivity.setOnClickListener {
            startActivity(Intent(this,ImageLabelActivity::class.java))
        }
        btnTextRecoglActivity.setOnClickListener {
            startActivity(Intent(this,TextRecogActivity::class.java))
        }

    }

}
