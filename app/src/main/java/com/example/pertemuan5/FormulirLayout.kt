package com.example.pertemuan5 // <-- SUDAH DISESUAIKAN

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
// HANYA IMPORT MATERIAL 3 (M3)
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
// ------------------------------
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pertemuan5.ui.theme.Pertemuan5Theme // <-- SUDAH DISESUAIKAN
import com.example.pertemuan5.R // <-- SUDAH DISESUAIKAN

// --- Warna Kustom ---
val HeaderPurple = Color(0xFF9C27B0)
val ButtonBlue = Color(0xFF3F51B5)
val LightBackground = Color(0xFFF5F5F5)

@Composable
fun FormPendaftaranScreen(modifier: Modifier = Modifier) {
    // --- Variabel untuk Input Formulir ---
    var inputNama by remember { mutableStateOf("") }
    var inputAlamat by remember { mutableStateOf("") }
    var selectedJenisKelamin by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("") }

    // --- Variabel untuk Hasil di Card (BARU) ---
    var displayedNama by remember { mutableStateOf("") }
    var displayedAlamat by remember { mutableStateOf("") }
    var displayedJenisKelamin by remember { mutableStateOf("") }
    var displayedStatus by remember { mutableStateOf("") }

    val jenisKelaminOptions = listOf(
        stringResource(R.string.option_pria),
        stringResource(R.string.option_wanita)
    )
    val statusOptions = listOf(
        stringResource(R.string.option_janda),
        stringResource(R.string.option_lajang),
        stringResource(R.string.option_duda)
    )

    // Layout utama
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackground)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {

        // --- HEADER UNGU ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HeaderPurple)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.form_title),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // --- CARD FORMULIR PUTIH ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // --- NAMA LENGKAP ---
                FormLabel(text = stringResource(R.string.label_nama_lengkap))
                OutlinedTextField(
                    value = inputNama,
                    onValueChange = { inputNama = it },
                    placeholder = { Text(stringResource(R.string.placeholder_nama)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // --- JENIS KELAMIN ---
                FormLabel(text = stringResource(R.string.label_jenis_kelamin))
                jenisKelaminOptions.forEach { option ->
                    RadioOption(
                        text = option,
                        selected = (selectedJenisKelamin == option),
                        onClick = { selectedJenisKelamin = option }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- STATUS PERKAWINAN ---
                FormLabel(text = stringResource(R.string.label_status_perkawinan))
                statusOptions.forEach { option ->
                    RadioOption(
                        text = option,
                        selected = (selectedStatus == option),
                        onClick = { selectedStatus = option }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- ALAMAT ---
                FormLabel(text = stringResource(R.string.label_alamat))
                OutlinedTextField(
                    value = inputAlamat,
                    onValueChange = { inputAlamat = it },
                    placeholder = { Text(stringResource(R.string.placeholder_alamat)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // --- TOMBOL SUBMIT ---
                Button(
                    onClick = {
                        // --- FUNGSI SUBMIT DI SINI ---
                        displayedNama = inputNama
                        displayedAlamat = inputAlamat
                        displayedJenisKelamin = selectedJenisKelamin
                        displayedStatus = selectedStatus
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = stringResource(R.string.button_submit),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        } // Akhir dari Card Formulir

        // --- CARD HASIL SUBMIT ---
        if (displayedNama.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D47A1)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.result_name_label)} $displayedNama",
                        color = Color.White
                    )
                    Text(
                        text = "${stringResource(R.string.result_gender_label)} $displayedJenisKelamin",
                        color = Color.White
                    )
                    Text(
                        text = "${stringResource(R.string.result_status_label)} $displayedStatus",
                        color = Color.White
                    )
                    Text(
                        text = "${stringResource(R.string.result_address_label)} $displayedAlamat",
                        color = Color.White
                    )
                }
            }
        }
    }
}

// --- Composable Bantuan ---
@Composable
fun FormLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun RadioOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text, modifier = Modifier.padding(start = 8.dp))
    }
}

// --- Preview ---
@Preview(showBackground = true)
@Composable
fun FormPendaftaranPreview() {
    Pertemuan5Theme(darkTheme = false) { // <-- SUDAH DISESUAIKAN
        FormPendaftaranScreen()
    }
}