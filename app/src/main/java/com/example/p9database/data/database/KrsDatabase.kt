package com.example.p9database.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.p9database.data.dao.MahasiswaDao
import com.example.p9database.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    abstract fun mahasiswaDao(): MahasiswaDao

    companion object{
        @Volatile
        private var INSTANCE: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java,
                    "krsDatabase"
                )
                    .build().also { INSTANCE = it }
            }
        }
    }
}