package com.example.ucp2_075.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_075.data.entity.Barang
import com.example.ucp2_075.data.entity.Supplier

@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)

abstract class TokoDatabase : RoomDatabase(){

    abstract fun barangDao(): Barang
    abstract fun supplierDao(): Barang

    companion object{
        @Volatile
        private var Instance: TokoDatabase? = null
        fun getDatabase(context: Context): TokoDatabase{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TokoDatabase::class.java,
                    "TokoDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}
