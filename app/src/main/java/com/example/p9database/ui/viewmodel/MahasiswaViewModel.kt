package com.example.p9database.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p9database.data.entity.Mahasiswa
import com.example.p9database.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs): ViewModel(){
    var uiState by mutableStateOf(MhsUiState())

    fun updateState(mahasiswEvent: MahasiswaEvent) {
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswEvent
        )
    }
    // validasi
    private fun validateFields(): Boolean {
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData() {
        val currentUiState = uiState.mahasiswaEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentUiState.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan"
                    )
                }
            }
        }
    }
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }

}
data class MhsUiState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val alamat: String? = null,
    val jenisKelamin: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)