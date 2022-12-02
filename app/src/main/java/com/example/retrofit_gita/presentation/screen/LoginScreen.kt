package com.example.retrofit_gita.presentation.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.databinding.ScreenLoginBinding
import com.example.retrofit_gita.presentation.viewmodel.LoginViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.LoginViewModelImp
import com.example.retrofit_gita.utils.amount
import com.example.retrofit_gita.utils.changeStatusBarColor
import com.example.retrofit_gita.utils.myAddTextChangedListener
import com.example.retrofit_gita.utils.myLog

class LoginScreen : Fragment(R.layout.screen_login) {
    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val vm: LoginViewModel by viewModels<LoginViewModelImp>()
    private var boolPassword = false
    private var boolName = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.apply {
            moveToVerifyScreenLiveData.observe(this@LoginScreen, moveToVerificationScreenObserver)
            moveToRegistrationLiveData.observe(this@LoginScreen, moveToRegistrationObserver)
            noConnectionLiveData.observe(this@LoginScreen, noConnectionObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.changeStatusBarColor(R.color.white)
        vm.apply {
            changeButtonStateLiveData.observe(viewLifecycleOwner, changeButtonStateObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            errorInputNameLiveData.observe(viewLifecycleOwner, errorInputNameObserver)
            errorInputPasswordLiveData.observe(viewLifecycleOwner, errorInputPasswordObserver)
            messageLiveData.observe(viewLifecycleOwner, messageObserver)
        }
        binding.apply {
            btLogin.setOnClickListener {
                vm.clickLogin(
                    inputPhone.amount(),
                    inputPassword.amount()
                )
            }
            btSignUp.setOnClickListener { vm.clickRegistration() }
            inputPhone.myAddTextChangedListener {
                boolName = it.length == 13
                check()
            }
            inputPassword.myAddTextChangedListener {
                boolPassword = it.length >= 3
                check()
            }
        }
    }

    private val messageObserver = Observer<String> {
        it.myLog()
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        binding.apply {
            inputPassword.text?.clear()
            inputPhone.text?.clear()
        }
    }

    private fun check() {
        binding.btLogin.isEnabled = boolPassword && boolName
    }

    private val moveToRegistrationObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_signUpScreen)
    }
    private val moveToVerificationScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_verificationScreen)
    }
    private val noConnectionObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "No connection with network!!!", Toast.LENGTH_SHORT).show()
    }
    private val changeButtonStateObserver = Observer<Boolean> { binding.btLogin.isEnabled = it }
    private val showProgressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
    private val errorInputNameObserver = Observer<String> { binding.inputPhone.error = it }
    private val errorInputPasswordObserver = Observer<String> { binding.inputPassword.error = it }
}