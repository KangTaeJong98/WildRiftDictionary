package com.taetae98.wildriftdictionary.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.Champion
import com.taetae98.wildriftdictionary.data.ChampionData
import com.taetae98.wildriftdictionary.databinding.FragmentChampionBinding

class ChampionFragment : BaseFragment<FragmentChampionBinding>(R.layout.fragment_champion) {
    init {
        setHasOptionsMenu(true)
    }

    companion object {
        private const val PAGE_COUNT = 6
        private const val ALL = 0
        private const val TOP = ALL + 1
        private const val JUNGLE = TOP + 1
        private const val MID = JUNGLE + 1
        private const val BOT = MID + 1
        private const val SUPPORTER = BOT + 1
    }

    private val championList by lazy { ChampionData.getInstance().champions.values.toMutableList().apply { sortBy { it.nameLocale } } }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_champion_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                findNavController().navigate(ChampionFragmentDirections.actionChampionFragmentToChampionSearchFragment())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun init() {
        super.init()
        initSupportActionBar()
        initViewPager()
        initTabLayoutMediator()
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager() {
        with(binding.viewPager) {
            adapter = object : FragmentStateAdapter(this@ChampionFragment) {
                override fun getItemCount(): Int {
                    return PAGE_COUNT
                }

                override fun createFragment(position: Int): Fragment {
                    return ChampionPageFragment().apply {
                        when(position) {
                            ALL -> {
                                submitList(championList)
                            }
                            TOP -> {
                                submitList(championList.filter {
                                    it.line.contains(Champion.Line.TOP)
                                }.toMutableList())
                            }
                            JUNGLE -> {
                                submitList(championList.filter {
                                    it.line.contains(Champion.Line.JUNGLE)
                                }.toMutableList())
                            }
                            MID -> {
                                submitList(championList.filter {
                                    it.line.contains(Champion.Line.MID)
                                }.toMutableList())
                            }
                            BOT -> {
                                submitList(championList.filter {
                                    it.line.contains(Champion.Line.BOT)
                                }.toMutableList())
                            }
                            SUPPORTER -> {
                                submitList(championList.filter {
                                    it.line.contains(Champion.Line.SUPPORTER)
                                }.toMutableList())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTabLayoutMediator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(when(position) {
                ALL -> {
                    R.drawable.ic_all
                }
                TOP -> {
                    R.drawable.ic_top
                }
                JUNGLE -> {
                    R.drawable.ic_jungle
                }
                MID -> {
                    R.drawable.ic_mid
                }
                BOT -> {
                    R.drawable.ic_bot
                }
                SUPPORTER -> {
                    R.drawable.ic_support
                }
                else -> {
                    throw IllegalStateException()
                }
            })
        }.attach()
    }
}