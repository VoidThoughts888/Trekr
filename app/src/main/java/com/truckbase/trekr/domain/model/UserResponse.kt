package com.truckbase.trekr.domain.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("avatar")
    val avatar: String?, // https://picsum.photos/640/640?r=3517
    @SerializedName("creationAt")
    val creationAt: String?, // 2023-07-19T07:11:23.000Z
    @SerializedName("email")
    val email: String?, // john@mail.com
    @SerializedName("id")
    val id: Int?, // 1
    @SerializedName("name")
    val name: String?, // Jhon
    @SerializedName("password")
    val password: String?, // changeme
    @SerializedName("role")
    val role: String?, // customer
    @SerializedName("updatedAt")
    val updatedAt: String? // 2023-07-19T07:11:23.000Z
)