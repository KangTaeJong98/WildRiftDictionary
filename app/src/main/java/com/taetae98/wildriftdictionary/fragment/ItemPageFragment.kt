package com.taetae98.wildriftdictionary.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.GridSpacingItemDecoration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ItemAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.databinding.FragmentItemPageBinding
import com.taetae98.wildriftdictionary.toDp

class ItemPageFragment : BaseFragment<FragmentItemPageBinding>(R.layout.fragment_item_page) {
    init {
        retainInstance = true
    }

    var list: List<Item> = emptyList()

    private val onClick by lazy { { item: Item -> findNavController().navigate(ItemFragmentDirections.actionItemFragmentToItemDialog(item))} }
    private val spacingItemDecoration by lazy { GridSpacingItemDecoration(1, 5.toDp(), RecyclerView.HORIZONTAL) }

    override fun init() {
        super.init()
        initUpgradedRecyclerView()
        initMidRecyclerView()
        initBasicRecyclerView()
    }

    private fun initUpgradedRecyclerView() {
        with(binding.upgradedRecyclerView) {
            adapter = ItemAdapter().apply {
                onClick = this@ItemPageFragment.onClick
                submitList(list.filter {
                    it.level == Item.Level.UPGRADED
                }.toMutableList())
            }
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }

    private fun initMidRecyclerView() {
        with(binding.midRecyclerView) {
            adapter = ItemAdapter().apply {
                onClick = this@ItemPageFragment.onClick

                val filtered = list.filter {
                    it.level == Item.Level.MID
                }

                if (filtered.isEmpty()) {
                    binding.midTextView.text = getString(R.string.enchantment_item)
                    submitList(list.filter { it.level == Item.Level.ENCHANTMENT })
                } else {
                    submitList(filtered.toMutableList())
                }
            }
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }

    private fun initBasicRecyclerView() {
        with(binding.basicRecyclerView) {
            adapter = ItemAdapter().apply {
                onClick = this@ItemPageFragment.onClick
                submitList(list.filter {
                    it.level == Item.Level.BASIC
                }.toMutableList())
            }
            layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }
}