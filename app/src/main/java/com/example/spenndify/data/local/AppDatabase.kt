package com.example.spenndify.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spenndify.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getUserDao():UserDao
}
