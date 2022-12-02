package com.example.retrofit_gita.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.databinding.ScreenSignUpBinding
import com.example.retrofit_gita.presentation.viewmodel.SignUpViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.SignUpViewModelImp
import com.example.retrofit_gita.utils.amount
import com.example.retrofit_gita.utils.myLog

class SignUpScreen : Fragment(R.layout.screen_sign_up) {
    private val binding by viewBinding(ScreenSignUpBinding::bind)
    private val vm: SignUpViewModel by viewModels<SignUpViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.apply {
            moveToLoginLiveData.observe(this@SignUpScreen, moveToLoginObserver)
            moveToVerifyScreenLiveData.observe(this@SignUpScreen, moveToVerifyScreenObserver)
            noConnectionLiveData.observe(this@SignUpScreen, noConnectionObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btLogInHere.setOnClickListener { vm.clickLogin() }
        binding.apply {
            btCreateAccount.setOnClickListener {
                vm.clickCreateAccount(
                    inputFirstName.amount(),
                    inputLastName.amount(),
                    inputPhone.amount().trim().replace("-",""),
                    inputPassword.amount(),
                    inputPasswordRety.amount()
                )
            }
        }
        vm.apply {
            changeButtonStateLiveData.observe(viewLifecycleOwner, changeButtonStateObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            errorInputFirstNameLiveData.observe(viewLifecycleOwner, errorInputFirstNameObserver)
            errorInputLastNameLiveData.observe(viewLifecycleOwner, errorInputLastNameObserver)
            errorInputPhoneLiveData.observe(viewLifecycleOwner, errorInputPhoneObserver)
            errorInputPasswordLiveData.observe(viewLifecycleOwner, errorInputPasswordObserver)
            errorInputReturnPasswordLiveData.observe(
                viewLifecycleOwner,
                errorInputReturnPasswordObserver
            )
            messageLiveData.observe(viewLifecycleOwner, messageObserver)
        }
    }

    private val moveToLoginObserver = Observer<Unit> {findNavController().popBackStack()}
    private val moveToVerifyScreenObserver = Observer<Unit> {findNavController().navigate(R.id.action_signUpScreen_to_verificationScreen)}
    private val noConnectionObserver = Observer<Unit> {}
    private val changeButtonStateObserver = Observer<Boolean> {binding.btCreateAccount.isEnabled = it}
    private val showProgressObserver = Observer<Boolean> {if (it) binding.progress.show() else binding.progress.hide()}
    private val errorInputFirstNameObserver = Observer<String> {binding.inputFirstName.error = it}
    private val errorInputLastNameObserver = Observer<String> {binding.inputLastName.error = it}
    private val errorInputPhoneObserver = Observer<String> {binding.inputPhone.error = it}
    private val errorInputPasswordObserver = Observer<String> {binding.inputPassword.error = it}
    private val errorInputReturnPasswordObserver = Observer<String> {binding.inputPasswordRety.error = it}
    private val messageObserver = Observer<String>  {     it.myLog()
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
}

