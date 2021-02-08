package com.taetae98.wildriftdictionary.fragment

import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.RuneAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.RuneData
import com.taetae98.wildriftdictionary.databinding.FragmentRuneBinding

class RuneFragment : BaseFragment<FragmentRuneBinding>(R.layout.fragment_rune) {
    override fun init() {
        super.init()
        initSupportActionBar()
        initRecyclerView()
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = RuneAdapter().apply {
                submitList(RuneData.getInstance().runes)
            }

            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.CENTER
            }
        }
    }
}