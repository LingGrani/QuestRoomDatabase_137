package com.example.p9database.ui.view.mahasiswa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p9database.ui.costumwidget.TopAppBar
import com.example.p9database.ui.navigation.AlamatNavigasi
import com.example.p9database.ui.viewmodel.FormErrorState
import com.example.p9database.ui.viewmodel.MahasiswaEvent
import com.example.p9database.ui.viewmodel.MahasiswaViewModel
import com.example.p9database.ui.viewmodel.MhsUiState
import com.example.p9database.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinationInsert : AlamatNavigasi {
    override val route: String = "insert"
}

@Preview(showBackground = true)
@Composable
fun FormMahasiswa(
    mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    onValueChange: (MahasiswaEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki-laki", "Perempuan")
    val kelas = listOf("A", "B", "C", "D", "E")

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.nama,
            onValueChange = {
                onValueChange(mahasiswaEvent.copy(nama = it))
            },
            label = { Text(text = "Nama") },
            isError = errorState.nama != null,
            placeholder = { Text(text = "Masukkan nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.nim,
            onValueChange = {
                onValueChange(mahasiswaEvent.copy(nim = it))
            },
            label = { Text(text = "NIM") },
            isError = errorState.nim != null,
            placeholder = { Text(text = "Masukkan NIM") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.nim ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Jenis Kelamin")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            jenisKelamin.forEach { jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = mahasiswaEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(mahasiswaEvent.copy(jenisKelamin = jk))
                        }
                    )
                    Text(text = jk)
                }
            }
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.alamat,
            onValueChange = {
                onValueChange(mahasiswaEvent.copy(alamat = it))
            },
            label = { Text(text = "Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text(text = "Masukkan Alamat") }
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
        Text("Kelas")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            kelas.forEach { kls ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = mahasiswaEvent.kelas == kls,
                        onClick = {
                            onValueChange(mahasiswaEvent.copy(kelas = kls))
                        }
                    )
                    Text(text = kls)
                }
            }
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mahasiswaEvent.angkatan,
            onValueChange = {
                onValueChange(mahasiswaEvent.copy(angkatan = it))
            },
            label = { Text(text = "Angkatan") },
            isError = errorState.angkatan != null,
            placeholder = { Text(text = "Masukkan Angkatan") }
        )
        Text(
            text = errorState.angkatan ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyMhs(
    modifier: Modifier = Modifier,
    onValueChange: (MahasiswaEvent) -> Unit = {},
    uiState: MhsUiState,
    onClick: () -> Unit = {}
    ){
    FormMahasiswa(
        mahasiswaEvent = uiState.mahasiswaEvent,
        onValueChange = onValueChange,
        errorState = uiState.isEntryValid,
        modifier = modifier.fillMaxWidth()
    )
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Simpan")
    }
}

@Composable
fun InsertMhsView(
    onBack: () -> Unit = {},
    onNavigate: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(
        factory = PenyediaViewModel.Factory
    )
){
    val uiState = viewModel.uiState
    val snackbarHotState = remember {SnackbarHostState()}
    val coroutineScope = rememberCoroutineScope()

    //observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHotState.showSnackbar(message)  // Tampilan Snackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHotState) }
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mahasiswa"
            )
            //isi Body
            InsertBodyMhs(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}