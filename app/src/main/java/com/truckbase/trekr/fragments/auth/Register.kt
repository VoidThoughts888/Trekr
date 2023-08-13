package com.truckbase.trekr.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.truckbase.trekr.R
import com.truckbase.trekr.data.repository.AuthRepository
import com.truckbase.trekr.databinding.FragmentRegisterBinding
import com.truckbase.trekr.domain.model.RegisterResponse
import com.truckbase.trekr.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = binding.etEmail.text
        val password = binding.etPassword.text
        val username = binding.etUsername.text
        val avatar = "https://unsplash.com/photos/Mv9hjnEUHR4"

        binding.apply {
            submitBtn.setOnClickListener {
                submitBtn.startAnimation()
                authRepository.register("$username", "$email", "$password", avatar)
                    .enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            println(response)
                            println(avatar)
                            when (response.code()) {
                                in 200..300 -> {
                                    submitBtn.revertAnimation()
                                    println(response.code())
                                    view.snackbar("Welcome New User")
                                    findNavController().navigate(R.id.action_register_to_login)
                                }
                                else -> {
                                    submitBtn.revertAnimation()
                                    view.snackbar("${response.code()}")
                                }
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            view.snackbar("${t.message}")
                        }

                    })
            }

            backArrow.setOnClickListener {
                findNavController().navigate(R.id.action_register_to_welcome)
            }
            loginRedirect.setOnClickListener {
                findNavController().navigate(R.id.action_register_to_login)
            }
        }
    }
}