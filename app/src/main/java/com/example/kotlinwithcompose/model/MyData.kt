package com.example.kotlinwithcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyData(var name:String, var age:Int) : Parcelable {
}