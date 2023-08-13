package com.truckbase.trekr.data.di

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.truckbase.trekr.data.local_storage.TokenManager
import com.truckbase.trekr.utils.Constants.BASE_URL
import com.truckbase.trekr.utils.Constants.NETWORK_TIMEOUT
import com.truckbase.trekr.data.repository.AuthRepository
import com.truckbase.trekr.data.services.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun ProvideBaseUrl()= BASE_URL

    @Provides
    @Singleton
    fun connectionTimeout()= NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun ProvideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val authToken = tokenManager.getToken()
            val modifiedRequest = if (authToken != null) {
                originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()
            } else {
                originalRequest
            }
            chain.proceed(modifiedRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRepository(apiServices: ApiServices): AuthRepository {
        return AuthRepository(apiServices)
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices=
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)
}