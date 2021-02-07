package com.taetae98.wildriftdictionary.fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.ActivityMainNavigationXmlDirections
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.*
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.databinding.FragmentChampionInformationBinding
import com.taetae98.wildriftdictionary.singleton.LocaleManager
import java.util.*

class ChampionInformationFragment : BaseFragment<FragmentChampionInformationBinding>(R.layout.fragment_champion_information) {
    private val args by navArgs<ChampionInformationFragmentArgs>()

    override fun init() {
        super.init()
        initCollapsingToolbar()
        initSupportActionBar()
        initImageView()
        initItemRecyclerView()
        initRuneRecyclerView()
        initSpellRecyclerView()
        initAbilityRecyclerView()
        initSubAbilityRecyclerView()
        initSkillRecyclerView()
        initOnUniverse()
    }

    private fun initCollapsingToolbar() {
        binding.title = args.champion.nameLocale
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initImageView() {
        binding.imageURL = args.champion.splashImageURL
    }

    private fun initItemRecyclerView() {
        with(binding.itemRecyclerView) {
            adapter = ItemAdapter().apply {
                submitList(args.champion.item)
            }
        }
    }

    private fun initRuneRecyclerView() {
        with(binding.runeRecyclerView) {
            adapter = RuneAdapter().apply {
                submitList(args.champion.rune)
            }
        }
    }

    private fun initSpellRecyclerView() {
        with(binding.spellRecyclerView) {
            adapter = SpellAdapter().apply {
                submitList(args.champion.spell)
            }
        }
    }

    private fun initAbilityRecyclerView() {
        with(binding.abilityRecyclerView) {
            adapter = AbilityAdapter().apply {
                submitList(args.champion.ability)
            }
        }
    }

    private fun initSubAbilityRecyclerView() {
        with(binding.subAbilityRecyclerView) {
            adapter = AbilityAdapter().apply {
                submitList(args.champion.subAbility)
            }
        }
    }

    private fun initSkillRecyclerView() {
        with(binding.skillRecyclerView) {
            adapter = SkillAdapter().apply {
                submitList(args.champion.skill)
            }
        }
    }

    private fun initOnUniverse() {
        binding.setOnUniverse {
            findNavController().navigate(ActivityMainNavigationXmlDirections.actionGlobalWebViewFragment("https://universe.leagueoflegends.com/${LocaleManager.getLoLLocale()}/champion/${args.champion.nameEn.toLowerCase(Locale.ROOT)}"))
        }
    }
}