package com.truckbase.trekr.domain.model


import com.google.gson.annotations.SerializedName

class ProductsResponse : ArrayList<ProductsResponse.ProductsResponseItem>(){
    data class ProductsResponseItem(
        @SerializedName("category")
        val category: Category?,
        @SerializedName("creationAt")
        val creationAt: String?, // 2023-08-01T21:34:53.000Z
        @SerializedName("description")
        val description: String?, // The beautiful range of Apple Naturalé that has an exciting mix of natural ingredients. With the Goodness of 100% Natural Ingredients
        @SerializedName("id")
        val id: Int?, // 12
        @SerializedName("images")
        val images: List<String?>?,
        @SerializedName("price")
        val price: Int?, // 192
        @SerializedName("title")
        val title: String?, // Tasty Fresh Ball(Kang)
        @SerializedName("updatedAt")
        val updatedAt: String? // 2023-08-02T10:17:53.000Z
    ) {
        data class Category(
            @SerializedName("creationAt")
            val creationAt: String?, // 2023-08-01T21:34:53.000Z
            @SerializedName("id")
            val id: Int?, // 1
            @SerializedName("image")
            val image: String?, // https://picsum.photos/640/640?r=4234
            @SerializedName("name")
            val name: String?, // Cloth
            @SerializedName("updatedAt")
            val updatedAt: String? // 2023-08-02T06:36:01.000Z
        )
    }
}