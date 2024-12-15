package com.example.p9database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.p9database.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>
    @Insert
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa
    )
    @Delete
    suspend fun deleteMahasiswa(
        mahasiswa: Mahasiswa
    )
    @Update
    suspend fun updateMahasiswa(
        mahasiswa: Mahasiswa
    )
}