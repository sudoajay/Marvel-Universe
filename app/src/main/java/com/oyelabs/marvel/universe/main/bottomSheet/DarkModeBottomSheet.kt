package com.oyelabs.marvel.universe.main.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.LayoutDarkModeBottomSheetBinding
import com.oyelabs.marvel.universe.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DarkModeBottomSheet(private var mainActivity: MainActivity) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val myDrawerView = layoutInflater.inflate(R.layout.layout_dark_mode_bottom_sheet, null)
        val binding = LayoutDarkModeBottomSheetBinding.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )
        binding.bottomSheet = this



        return binding.root
    }


    fun getValue(): String {
        return if (mainActivity.currentTheme!!.isEmpty()) getString(R.string.system_default_text) else mainActivity.currentTheme!!
    }

    fun setValue(value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mainActivity.protoManager.setCurrentTheme(value)
        }
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().finish()
        startActivity(intent)


    }


}

