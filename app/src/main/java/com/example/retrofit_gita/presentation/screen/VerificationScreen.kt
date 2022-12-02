package com.example.retrofit_gita.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.databinding.ScreenVerificationBinding
import com.example.retrofit_gita.presentation.viewmodel.VerificationViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.VerificationViewModelImp
import com.example.retrofit_gita.utils.changeStatusBarColor

class VerificationScreen : Fragment(R.layout.screen_verification) {
    private val binding by viewBinding(ScreenVerificationBinding::bind)
    private val vm: VerificationViewModel by viewModels<VerificationViewModelImp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.changeStatusBarColor(R.color.white)
        vm.apply {
            changeProgressLiveData.observe(viewLifecycleOwner, changeProgressObserver)
            changeTimerTextLiveDAta.observe(viewLifecycleOwner, changeTimerTextObserver)
            moveToMainScreenLiveData.observe(viewLifecycleOwner, moveToMainScreenObserver)
            changeVisibilityProgressLiveData.observe(
                viewLifecycleOwner,
                changeVisibilityProgressObserver
            )
        }
    }

    private val changeTimerTextObserver = Observer<String> { binding.tvTimer.text = it }
    private val changeProgressObserver = Observer<Int> { binding.progress.progress = it }
    private val moveToMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verificationScreen_to_mainScreen)
    }
    private val changeVisibilityProgressObserver = Observer<Boolean> {
        binding.btVerify.isEnabled = it
        if (it) {
            binding.progress.visibility = View.VISIBLE
            binding.tvTimer.visibility = View.VISIBLE
            binding.btSendAgain.visibility = View.GONE
        } else {
            binding.progress.visibility = View.GONE
            binding.tvTimer.visibility = View.GONE
            binding.btSendAgain.visibility = View.VISIBLE
        }

    }

}