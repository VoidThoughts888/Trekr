package com.truckbase.trekr.domain.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String?, // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjU4LCJpYXQiOjE2ODg0Njc1OTMsImV4cCI6MTY5MDE5NTU5M30.JNKgklZpbNSVxw2GeBukNfdlC7p39_bJHHoBeL67jFY
    @SerializedName("refresh_token")
    val refreshToken: String? // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjU4LCJpYXQiOjE2ODg0Njc1OTMsImV4cCI6MTY4ODUwMzU5M30.0MVXU33JFNytlTB3maGCszOT9YbtEZv3Dzwz7FgeeD0
)