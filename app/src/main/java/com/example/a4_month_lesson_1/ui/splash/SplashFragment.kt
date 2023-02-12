package com.example.a4_month_lesson_1.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentHomeBinding
import com.example.a4_month_lesson_1.databinding.FragmentSplashBinding
import kotlinx.coroutines.android.HandlerDispatcher


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         Handler(Looper.getMainLooper()).postDelayed({
             findNavController().navigate(R.id.navigation_home)
         }, 3000)
    }
}