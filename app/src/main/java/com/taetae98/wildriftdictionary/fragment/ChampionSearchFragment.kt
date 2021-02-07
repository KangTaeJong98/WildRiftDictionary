package com.taetae98.wildriftdictionary.fragment

import androidx.core.widget.addTextChangedListener
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ChampionAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.ChampionData
import com.taetae98.wildriftdictionary.databinding.FragmentChampionSearchBinding
import java.util.*

class ChampionSearchFragment : BaseFragment<FragmentChampionSearchBinding>(R.layout.fragment_champion_search) {
    private val championAdapter by lazy { ChampionAdapter().apply { submitList(ChampionData.getInstance().champions.values.toMutableList().apply { sortBy { it.nameLocale } }) } }

    override fun init() {
        super.init()
        initSetSupportActionBar()
        initTextInputLayout()
        initRecyclerView()
    }

    private fun initSetSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initTextInputLayout() {
        with(binding.inputLayout) {
            editText?.addTextChangedListener {
                it?.let {
                    championAdapter.submitList(ChampionData.getInstance().champions.values.
                    filter { champion ->
                        champion.nameEn.toLowerCase(Locale.ROOT).contains(it.toString().toLowerCase(Locale.ROOT)) ||
                                champion.nameLocale.contains(it)
                    }.toMutableList().apply {
                        sortBy { champion ->
                            champion.nameLocale
                        }
                    })
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = championAdapter
        }
    }
}