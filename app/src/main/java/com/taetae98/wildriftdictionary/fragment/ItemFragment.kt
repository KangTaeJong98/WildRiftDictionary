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
import com.taetae98.wildriftdictionary.data.Item
import com.taetae98.wildriftdictionary.data.ItemData
import com.taetae98.wildriftdictionary.databinding.FragmentChampionBinding

class ItemFragment : BaseFragment<FragmentChampionBinding>(R.layout.fragment_champion) {
    init {
        setHasOptionsMenu(true)
    }

    companion object {
        private const val PAGE_COUNT = 4
        private const val PHYSICAL = 0
        private const val MAGIC = PHYSICAL + 1
        private const val DEFENSE = MAGIC + 1
        private const val BOOTS = DEFENSE + 1
    }

    private val itemList by lazy { ItemData.getInstance().items }

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
            adapter = object : FragmentStateAdapter(this@ItemFragment) {
                override fun getItemCount(): Int {
                    return PAGE_COUNT
                }

                override fun createFragment(position: Int): Fragment {
                    return ItemPageFragment().apply {
                        when(position) {
                            PHYSICAL -> {
                                submitList(itemList.filter { it.type == Item.Type.PHYSICAL }.toMutableList())
                            }
                            MAGIC -> {
                                submitList(itemList.filter { it.type == Item.Type.MAGIC }.toMutableList())
                            }
                            DEFENSE -> {
                                submitList(itemList.filter { it.type == Item.Type.DEFENSE }.toMutableList())
                            }
                            BOOTS -> {
                                submitList(itemList.filter { it.type == Item.Type.BOOTS }.toMutableList())
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
                PHYSICAL -> {
                    R.drawable.ic_physical
                }
                MAGIC -> {
                    R.drawable.ic_magic
                }
                DEFENSE -> {
                    R.drawable.ic_defense
                }
                BOOTS -> {
                    R.drawable.ic_boots
                }
                else -> {
                    throw IllegalStateException()
                }
            })
        }.attach()
    }
}