package com.truckbase.trekr.data.services

import com.truckbase.trekr.domain.model.*
import retrofit2.Call
import retrofit2.http.*


interface ApiServices {
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("users/")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("avatar") avatar: String
    ): Call<RegisterResponse>

    @GET("auth/profile")
    fun getUserData(): Call<UserResponse>

    @GET("categories")
    fun getCategories(): Call<CategoryResponse>

    @GET("categories/{id}/products")
    fun getProductsByCategory(
        @Path("id") id: String
    ): Call<List<ProductsResponse>>
}