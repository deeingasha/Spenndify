package com.example.spenndify.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "idNumber")
    var idNumber: String,


    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "telNumber")
    var phone: String,

    @ColumnInfo(name = "passion")
    var questionOne: String,

    @ColumnInfo(name = "petsName")
    var questionTwo: String,

    @ColumnInfo(name = "favouriteColor")
    var questionThree: String,

    @ColumnInfo(name = "password")
    var password: String,
)
