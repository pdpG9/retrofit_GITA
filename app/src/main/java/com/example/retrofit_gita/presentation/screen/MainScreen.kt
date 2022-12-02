package com.example.retrofit_gita.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofit_gita.R
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.databinding.ScreenMainBinding
import com.example.retrofit_gita.presentation.adapter.BookAdapter
import com.example.retrofit_gita.presentation.viewmodel.MainViewModel
import com.example.retrofit_gita.presentation.viewmodel.imp.MainViewModelImp
import com.example.retrofit_gita.utils.myLog

class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val vm: MainViewModel by viewModels<MainViewModelImp>()
    private val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.moveToAddButtonLiveData.observe(this, moveToAddScreenObserver)
        vm.moveToBookInfoLiveData.observe(this, moveToBookInfoObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRv()
        vm.apply {
            messageLiveData.observe(viewLifecycleOwner, messageObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            booksLiveData.observe(viewLifecycleOwner, booksObserver)
        }
    }

    private fun initRv() {
        binding.rvBook.adapter = adapter
        binding.rvBook.layoutManager = GridLayoutManager(requireContext(), 2)
    }
    private val messageObserver = Observer<String> {
        it.myLog()
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
    private val booksObserver = Observer<List<BookResponse>> { adapter.submitList(it) }
    private val showProgressObserver = Observer<Boolean> { if (it) binding.progress.show() else binding.progress.hide() }
    private val moveToBookInfoObserver = Observer<BookResponse> {
        findNavController().navigate(R.id.action_mainScreen_to_infoScreen, bundleOf("BOOK" to it)) }
    private val moveToAddScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_mainScreen_to_addBookScreen) }

}