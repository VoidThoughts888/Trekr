package com.truckbase.trekr.domain.model


import com.google.gson.annotations.SerializedName

class CategoryResponse : ArrayList<CategoryResponse.CategoryResponseItem>() {
    data class CategoryResponseItem(
        @SerializedName("creationAt")
        val creationAt: String?, // 2023-07-27T10:30:19.000Z
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("image")
        val image: String?, // https://picsum.photos/640/640?r=5493
        @SerializedName("name")
        val name: String?, // Clothes
        @SerializedName("updatedAt")
        val updatedAt: String? // 2023-07-27T10:30:19.000Z
    )
}