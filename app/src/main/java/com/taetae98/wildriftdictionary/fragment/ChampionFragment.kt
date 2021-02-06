package com.taetae98.wildriftdictionary.fragment

import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.ChampionData
import com.taetae98.wildriftdictionary.databinding.FragmentChampionBinding

class ChampionFragment : BaseFragment<FragmentChampionBinding>(R.layout.fragment_champion) {
    override fun init() {
        super.init()
        ChampionData.getInstance().champions
    }
}