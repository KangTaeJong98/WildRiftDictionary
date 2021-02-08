package com.taetae98.wildriftdictionary.fragment

import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.NewsAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.NewsData
import com.taetae98.wildriftdictionary.databinding.FragmentMainBinding

class NewsFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val data = NewsData.getInstance()

    override fun init() {
        super.init()
        initSupportActionBar()
        initSpecialNewsRecyclerView()
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initSpecialNewsRecyclerView() {
        with(binding.recyclerView) {
            adapter = NewsAdapter().apply {
                submitList(data.news)
            }
        }
    }
}