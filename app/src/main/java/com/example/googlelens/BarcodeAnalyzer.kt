package com.example.googlelens

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer:ImageAnalysis.Analyzer {
    val scanner =BarcodeScanning.getClient()
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("BARCODE","imageAnalyzed")
        imageProxy.image?.let {
            val inputImage=InputImage.fromMediaImage(it,imageProxy.imageInfo.rotationDegrees)
            scanner.process(inputImage)
                .addOnSuccessListener { codes ->   //if image is available
                    codes.forEach { barcode ->
                        Log.d(
                            "BARCODE", """
                            Format = ${barcode.format}
                            Value = ${barcode.rawValue}
                        """.trimIndent()
                        )
                    }
                }
                .addOnFailureListener { ex ->
                    Log.e("BARCODE", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }?:imageProxy.close() // close if image not found either
    }
}