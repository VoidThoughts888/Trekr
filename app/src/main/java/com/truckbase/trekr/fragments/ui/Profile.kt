package com.truckbase.trekr.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.truckbase.trekr.R
import com.truckbase.trekr.data.di.ApiModule
import com.truckbase.trekr.data.local_storage.TokenManager
import com.truckbase.trekr.data.repository.AuthRepository
import com.truckbase.trekr.databinding.FragmentProfileBinding
import com.truckbase.trekr.domain.model.UserResponse
import com.truckbase.trekr.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var tokenManager: TokenManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        binding.apply {
            var token = tokenManager.getToken()
            println(token)
            if (token != null){
                authRepository.getUser(token).enqueue(object : Callback<UserResponse>{
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        username.text = response.body()?.name
                        email.text = response.body()?.email
                        context?.let {
                            Glide.with(it)
                                .load("https://picsum.photos/640/640?r=3517")
                                .centerCrop()
                                .into(userProfile)
                        }
                        var name = response.body()?.name
                        var avatar = response.body()?.avatar

                        var dataPassing = name?.let {
                            if (avatar != null) {
                                HomeFragment.newInstance(it,avatar)
                            }
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        t.message?.let { view.snackbar(it) }
                    }

                })
            }else{
                view.snackbar("User Not Found")
            }

        }
        return binding.root
    }


}