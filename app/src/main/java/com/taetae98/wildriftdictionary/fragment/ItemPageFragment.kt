package com.taetae98.wildriftdictionary.fragment

import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ItemAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.databinding.FragmentItemPageBinding

class ItemPageFragment : BaseFragment<FragmentItemPageBinding>(R.layout.fragment_item_page) {
    init {
        retainInstance = true
    }

    private val itemAdapter by lazy { ItemAdapter() }

    override fun init() {
        super.init()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = itemAdapter
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
            }
        }
    }

    fun submitList(list: MutableList<Item>) {
        itemAdapter.submitList(list)
    }
}