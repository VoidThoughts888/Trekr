package com.truckbase.trekr.data.repository

import com.truckbase.trekr.data.services.ApiServices
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AuthRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    fun login(email: String, password: String) = apiServices.login(email, password)
    fun register(name: String, email: String, password: String, avatar: String) =
        apiServices.register(name, email, password, avatar)
    fun getUser(accessToken: String)= apiServices.getUserData()
    fun getCategory()=apiServices.getCategories()
    fun getProductsByCategory(id: String)=apiServices.getProductsByCategory(id)
}