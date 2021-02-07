package com.taetae98.wildriftdictionary.fragment

import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.AbilityAdapter
import com.taetae98.wildriftdictionary.adapter.ItemAdapter
import com.taetae98.wildriftdictionary.adapter.SkillAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.ItemData
import com.taetae98.wildriftdictionary.databinding.FragmentChampionInformationBinding

class ChampionInformationFragment : BaseFragment<FragmentChampionInformationBinding>(R.layout.fragment_champion_information) {
    private val args by navArgs<ChampionInformationFragmentArgs>()

    override fun init() {
        ItemData.getInstance().items
        super.init()
        initCollapsingToolbar()
        initSupportActionBar()
        initImageView()
        initItemRecyclerView()
        initAbilityRecyclerView()
        initSubAbilityRecyclerView()
        initSkillRecyclerView()
    }

    private fun initCollapsingToolbar() {
        binding.title = args.champion.nameKr
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initImageView() {
        binding.imageURL = args.champion.splashImageURL
    }

    private fun initItemRecyclerView() {
        args.champion.initItem()
        with(binding.itemRecyclerView) {
            adapter = ItemAdapter().apply {
                submitList(args.champion.item)
            }
        }
    }

    private fun initAbilityRecyclerView() {
        args.champion.initAbility()
        with(binding.abilityRecyclerView) {
            adapter = AbilityAdapter().apply {
                submitList(args.champion.ability)
            }
        }
    }

    private fun initSubAbilityRecyclerView() {
        args.champion.initSubAbility()
        with(binding.subAbilityRecyclerView) {
            adapter = AbilityAdapter().apply {
                submitList(args.champion.subAbility)
            }
        }
    }

    private fun initSkillRecyclerView() {
        args.champion.initSkill()
        with(binding.skillRecyclerView) {
            adapter = SkillAdapter().apply {
                submitList(args.champion.skill)
            }
        }
    }
}