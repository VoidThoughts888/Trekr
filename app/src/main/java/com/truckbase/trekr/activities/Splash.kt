package com.truckbase.trekr.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.truckbase.trekr.R
import com.truckbase.trekr.data.local_storage.TokenManager
import com.truckbase.trekr.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Splash : AppCompatActivity() {
    @Inject lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var token = tokenManager.getToken()
        Handler().postDelayed({
             if (token == null){
                 startNewActivity(AuthActivity::class.java)
             }else{
                 startNewActivity(Home::class.java)
             }
        },5L)
    }
}