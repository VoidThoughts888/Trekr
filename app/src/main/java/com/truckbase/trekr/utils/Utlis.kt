package com.truckbase.trekr.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.truckbase.trekr.data.di.Resources
import com.truckbase.trekr.fragments.auth.Login

fun <A: Activity> Activity.startNewActivity(activity : Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled : Boolean){
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (()->Unit)?=null){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resources.Failure,
    retry:  (()-> Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
        failure.errorCode == 401 ->{
            if (this is Login){
                requireView().snackbar("You have entered an incorrect email or password")
            }else{

            }
        }
        else ->{
            val error = failure.errorResponse?.contentType().toString()
            requireView().snackbar(error)
        }
    }
}

fun View.loadImage(imgUrl : String,imgView: ImageView){
    Glide.with(this)
        .load(imgUrl)
        .centerCrop()
        .into(imgView)
}