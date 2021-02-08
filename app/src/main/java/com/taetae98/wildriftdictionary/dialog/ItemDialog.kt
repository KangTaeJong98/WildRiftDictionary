package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.databinding.DialogItemBinding

class ItemDialog(context: Context, private var item: Item = Item()) : BaseDialog<DialogItemBinding>(context, R.layout.dialog_item) {
    fun bind(item: Item) {
        this.item = item
        initItem()
    }

    override fun init() {
        super.init()
        initItem()
    }

    private fun initItem() {
        binding.item = item
    }
}