package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.databinding.DialogItemBinding

class ItemDialog : BaseDialog<DialogItemBinding>(R.layout.dialog_item) {
    private val arg by navArgs<ItemDialogArgs>()

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun init() {
        super.init()
        initItem()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun initItem() {
        binding.item = arg.item
    }
}