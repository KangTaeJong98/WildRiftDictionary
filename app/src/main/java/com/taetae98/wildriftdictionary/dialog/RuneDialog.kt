package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.databinding.DialogRuneBinding

class RuneDialog : BaseDialog<DialogRuneBinding>(R.layout.dialog_rune) {
    private val args by navArgs<RuneDialogArgs>()

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun init() {
        super.init()
        initRune()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun initRune() {
        binding.rune = args.rune
    }
}