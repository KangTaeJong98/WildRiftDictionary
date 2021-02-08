package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.Rune
import com.taetae98.wildriftdictionary.databinding.DialogRuneBinding

class RuneDialog(context: Context, private var rune: Rune = Rune()) : BaseDialog<DialogRuneBinding>(context, R.layout.dialog_rune) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun bind(rune: Rune) {
        this.rune = rune
        initItem()
    }

    override fun init() {
        super.init()
        initItem()
    }

    private fun initItem() {
        binding.rune = rune
    }
}