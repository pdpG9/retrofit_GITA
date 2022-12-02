package com.example.retrofit_gita.presentation.screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.databinding.ScreenSplashBinding
import com.example.retrofit_gita.presentation.viewmodel.SplashViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.SplashViewModelImp
import com.example.retrofit_gita.utils.changeNavigationBarColor
import com.example.retrofit_gita.utils.changeStatusBarColor

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val vm: SplashViewModel by viewModels<SplashViewModelImp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.changeStatusBarColor(R.color.main_color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireActivity().window.changeNavigationBarColor(R.color.main_color)
        }
        vm.apply {
            moveToLoginScreenLiveData.observe(viewLifecycleOwner, moveToLoginScreenObserver)
            moveToMainScreenLiveData.observe(viewLifecycleOwner, moveToMainScreenObserver)
        }
    }

    private val moveToLoginScreenObserver =
        Observer<Unit> { findNavController().navigate(R.id.action_splashScreen_to_loginScreen) }
    private val moveToMainScreenObserver =
        Observer<Unit> { findNavController().navigate(R.id.action_splashScreen_to_mainScreen) }
}