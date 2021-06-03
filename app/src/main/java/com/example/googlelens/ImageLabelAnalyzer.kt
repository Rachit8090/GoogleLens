package com.example.googlelens

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabelerOptionsBase
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageLabelAnalyzer:ImageAnalysis.Analyzer {
    private val labeler = ImageLabeling.getClient(ImageLabelerOptions.Builder()
        .setConfidenceThreshold(0.7F)
        .build()
    )
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("LABEL","labelimageAnalyzed")

        imageProxy.image?.let {

            val inputImage= InputImage.fromMediaImage(it,imageProxy.imageInfo.rotationDegrees)
            labeler.process(inputImage)
                .addOnSuccessListener { labels ->
                    labels.forEach { label ->
                        Log.d(
                            "LABEL", """
                            Format = ${label.text}
                            Value = ${label.confidence}
                        """.trimIndent()
                        )
                    }
                }
                .addOnFailureListener { ex ->
                    Log.e("Labelimage", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }?:imageProxy.close() // close if image not found either


    }
}