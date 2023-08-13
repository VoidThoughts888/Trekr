package com.truckbase.trekr.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisterResponse(
    @SerializedName("avatar")
    val avatar: String?, // https://unsplash.com/photos/Mv9hjnEUHR4
    @SerializedName("creationAt")
    val creationAt: String?, // 2023-07-04T10:55:44.000Z
    @SerializedName("email")
    val email: String?, // user2@gmail.com
    @SerializedName("id")
    val id: Int?, // 61
    @SerializedName("name")
    val name: String?, // David
    @SerializedName("password")
    val password: String?, // VoidThoughts
    @SerializedName("role")
    val role: String?, // customer
    @SerializedName("updatedAt")
    val updatedAt: String? // 2023-07-04T10:55:44.000Z
)