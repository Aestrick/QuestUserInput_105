package com.example.pertemuan5 // <-- SUDAH DISESUAIKAN

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pertemuan5.ui.theme.Pertemuan5Theme // <-- SUDAH DISESUAIKAN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Ganti ke tema project-mu
            Pertemuan5Theme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil layar formulir pendaftaran
                    FormPendaftaranScreen()
                }
            }
        }
    }
}