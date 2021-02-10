package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.GridSpacingItemDecoration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ItemAdapter
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.ItemData
import com.taetae98.wildriftdictionary.databinding.DialogItemSearchBinding
import com.taetae98.wildriftdictionary.toDp
import java.util.*

class ItemSearchDialog : BaseDialog<DialogItemSearchBinding>(R.layout.dialog_item_search) {
    private val itemAdapter by lazy {
        ItemAdapter().apply {
            onClick = {
                findNavController().navigate(ItemSearchDialogDirections.actionItemSearchDialogToItemDialog(it))
            }
            submitList(ItemData.getInstance().items.toMutableList())
        }
    }

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun init() {
        super.init()
        initTextInputLayout()
        initRecyclerView()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun initTextInputLayout() {
        with(binding.inputLayout) {
            editText?.addTextChangedListener {
                it?.let {
                    itemAdapter.submitList(ItemData.getInstance().items.
                    filter { item ->
                        item.name.toLowerCase(Locale.ROOT).contains(it.toString().toLowerCase(Locale.ROOT))
                    }.toMutableList())
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = itemAdapter
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(GridSpacingItemDecoration(1, 5.toDp(), RecyclerView.HORIZONTAL))
        }
    }
}