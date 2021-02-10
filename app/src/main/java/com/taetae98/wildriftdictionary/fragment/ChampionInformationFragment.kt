package com.taetae98.wildriftdictionary.fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.GridSpacingItemDecoration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.*
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.databinding.FragmentChampionInformationBinding
import com.taetae98.wildriftdictionary.singleton.LocaleManager
import com.taetae98.wildriftdictionary.toDp
import java.util.*

class ChampionInformationFragment : BaseFragment<FragmentChampionInformationBinding>(R.layout.fragment_champion_information) {
    private val args by navArgs<ChampionInformationFragmentArgs>()
    private val spacingItemDecoration by lazy { GridSpacingItemDecoration(1, 10.toDp(), RecyclerView.HORIZONTAL) }
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
                onClick = {
                    findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToItemDialog(it))
                }
            }
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }

    private fun initRuneRecyclerView() {
        with(binding.runeRecyclerView) {
            adapter = RuneAdapter().apply {
                submitList(args.champion.rune)
                onClick = {
                    findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToRuneDialog(it))
                }
            }
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }

    private fun initSpellRecyclerView() {
        with(binding.spellRecyclerView) {
            adapter = SpellAdapter().apply {
                submitList(args.champion.spell)
                onClick = {
                    findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToSpellDialog(it))
                }
            }
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
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
                onClick = {
                    findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToSkillDialog(it))
                }
            }
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.CENTER
            }
            addItemDecoration(spacingItemDecoration)
        }
    }

    private fun initOnUniverse() {
        binding.setOnUniverse {
            findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToWebViewFragment("https://universe.leagueoflegends.com/${LocaleManager.getLoLLocale()}/champion/${args.champion.nameEn.toLowerCase(Locale.ROOT)}"))
        }
    }
}