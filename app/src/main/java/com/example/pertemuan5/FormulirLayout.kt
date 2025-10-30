package com.example.pertemuan5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pertemuan5.ui.theme.Pertemuan5Theme

import androidx.compose.foundation.layout.systemBarsPadding

@Composable
fun FormulirScreen(modifier: Modifier = Modifier) {
    var inputNama by remember { mutableStateOf("") }
    var inputAlamat by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var displayedNama by remember { mutableStateOf("") }
    var displayedAlamat by remember { mutableStateOf("") }
    var displayedGender by remember { mutableStateOf("") }
    val genderOptions = listOf("Laki-laki", "Perempuan")

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            // ---------------------------------
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(text = "Formulir Data Diri", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nama Lengkap
        OutlinedTextField(
            value = inputNama,
            onValueChange = { inputNama = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jenis Kelamin", style = MaterialTheme.typography.bodyLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            genderOptions.forEach { gender ->
                Row(
                    Modifier
                        .selectable(
                            selected = (selectedGender == gender),
                            onClick = { selectedGender = gender }
                        )
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedGender == gender),
                        onClick = { selectedGender = gender }
                    )
                    Text(text = gender, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Alamat Lengkap
        OutlinedTextField(
            value = inputAlamat,
            onValueChange = { inputAlamat = it },
            label = { Text("Alamat Lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                displayedNama = inputNama
                displayedAlamat = inputAlamat
                displayedGender = selectedGender
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("TAMPILKAN DATA")
        }

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.DarkGray
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Nama : $displayedNama",
                    color = Color.White
                )
                Text(
                    text = "Gender : $displayedGender",
                    color = Color.White
                )
                Text(
                    text = "Alamat : $displayedAlamat",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormulirPreview() {
    Pertemuan5Theme {
        FormulirScreen()
    }
}