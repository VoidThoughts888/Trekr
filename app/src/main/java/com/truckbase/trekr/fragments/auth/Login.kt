package com.truckbase.trekr.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.truckbase.trekr.R
import com.truckbase.trekr.activities.Home
import com.truckbase.trekr.data.di.ApiModule
import com.truckbase.trekr.data.local_storage.TokenManager
import com.truckbase.trekr.data.repository.AuthRepository
import com.truckbase.trekr.databinding.FragmentLoginBinding
import com.truckbase.trekr.domain.model.LoginResponse
import com.truckbase.trekr.utils.snackbar
import com.truckbase.trekr.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http2.Http2Reader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var authToken: TokenManager

    @Inject
    lateinit var authRepository: AuthRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonLogin = binding.loginBtn
        val email = binding.etEmail.text
        val password = binding.etPassword.text

        buttonLogin.setOnClickListener {
            buttonLogin.startAnimation()
            authRepository.login("$email", "$password").enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    buttonLogin.revertAnimation()
                    print(email)
                    print(password)
                    println(response)
                    when (response.code()) {
                        in 200..300 -> {
                            response.body().let {
                                val token = "${it?.accessToken}"
                                authToken.saveToken(token)
                                println(token)
                                requireActivity().startNewActivity(Home::class.java)
                                view.snackbar("Home")
                            }
                        }
                        else -> {
                            buttonLogin.revertAnimation()
                            view.snackbar("${response.code()}")
                        }
                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    buttonLogin.revertAnimation()
                    view.snackbar(" Network Error ${t.message}")
                }

            })
        }

        binding.apply {
            backArrow.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_welcome)
            }

            registerRedirect.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_register)
            }
        }
    }

}