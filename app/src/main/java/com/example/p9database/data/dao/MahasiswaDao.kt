package com.example.p9database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.p9database.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>
    @Insert
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa
    )

}