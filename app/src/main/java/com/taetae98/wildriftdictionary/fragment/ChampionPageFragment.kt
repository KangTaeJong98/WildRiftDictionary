package com.taetae98.wildriftdictionary.fragment

import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ChampionAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.Champion
import com.taetae98.wildriftdictionary.databinding.FragmentChampionPageBinding

class ChampionPageFragment : BaseFragment<FragmentChampionPageBinding>(R.layout.fragment_champion_page) {
    init {
        retainInstance = true
    }

    private val championAdapter by lazy { ChampionAdapter() }

    override fun init() {
        super.init()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = championAdapter
        }
    }

    fun submitList(list: MutableList<Champion>) {
        championAdapter.submitList(list)
    }
}