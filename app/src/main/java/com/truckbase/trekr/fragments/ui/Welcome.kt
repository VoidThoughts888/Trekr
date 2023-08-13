package com.truckbase.trekr.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.truckbase.trekr.R
import com.truckbase.trekr.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            toLogin.setOnClickListener {
                findNavController().navigate(R.id.action_welcome_to_login)
            }
            toRegister.setOnClickListener {
                findNavController().navigate(R.id.action_welcome_to_register)
            }
        }
    }
}