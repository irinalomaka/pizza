package com.nennos.pizza.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(
    @field:SerializedName("imageUrl")
    var imageUrl: String? = null,

    @field:SerializedName("name")
    var name: String = "",

    @field:SerializedName("ingredients")
    var ingredientIds: ArrayList<Int> = ArrayList()
) : Parcelable