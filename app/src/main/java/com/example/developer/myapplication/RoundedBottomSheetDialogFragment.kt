package com.example.developer.myapplication

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.myapplication.databinding.DialogOptionsLayoutBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class RoundedBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

}

class MenuFragment : RoundedBottomSheetDialogFragment() {

    @Inject
    lateinit var bindingInterface: ProjectBindingInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DialogOptionsLayoutBinding>(inflater, R.layout.dialog_options_layout,
                container, false, bindingInterface)
        return binding.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}