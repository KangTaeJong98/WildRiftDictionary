package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.databinding.DialogItemBinding

class ItemDialog(context: Context, private var item: Item = Item()) : BaseDialog<DialogItemBinding>(context, R.layout.dialog_item) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

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