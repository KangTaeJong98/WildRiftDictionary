package com.taetae98.wildriftdictionary.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.taetae98.wildriftdictionary.GridSpacingItemDecoration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.RuneAdapter
import com.taetae98.wildriftdictionary.base.BaseFragment
import com.taetae98.wildriftdictionary.data.Rune
import com.taetae98.wildriftdictionary.data.RuneData
import com.taetae98.wildriftdictionary.databinding.FragmentRuneBinding
import com.taetae98.wildriftdictionary.toDp

class RuneFragment : BaseFragment<FragmentRuneBinding>(R.layout.fragment_rune) {
    private val onClick by lazy { { rune: Rune -> findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneDialog(rune))} }
    private val gridSpacingItemDecoration by lazy { GridSpacingItemDecoration(1, 10.toDp(), RecyclerView.HORIZONTAL) }

    override fun init() {
        super.init()
        initSupportActionBar()
        initKeyStoneRecyclerView()
        initDominationRecyclerView()
        initResolveRecyclerView()
        initInspirationRecyclerView()
    }

    private fun initSupportActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initKeyStoneRecyclerView() {
        with(binding.keyStoneRecyclerView) {
            adapter = RuneAdapter().apply {
                onClick = this@RuneFragment.onClick
                layoutManager = FlexboxLayoutManager(requireContext()).apply {
                    justifyContent = JustifyContent.CENTER
                }
                submitList(RuneData.getInstance().runes.filter {
                    it.type == Rune.Type.KEY_STONE
                })
                addItemDecoration(gridSpacingItemDecoration)
            }
        }
    }

    private fun initDominationRecyclerView() {
        with(binding.dominationRecyclerView) {
            adapter = RuneAdapter().apply {
                onClick = this@RuneFragment.onClick
                layoutManager = FlexboxLayoutManager(requireContext()).apply {
                    justifyContent = JustifyContent.CENTER
                }
                submitList(RuneData.getInstance().runes.filter {
                    it.type == Rune.Type.DOMINATION
                })
                addItemDecoration(gridSpacingItemDecoration)
            }
        }
    }

    private fun initResolveRecyclerView() {
        with(binding.resolveRecyclerView) {
            adapter = RuneAdapter().apply {
                onClick = this@RuneFragment.onClick
                layoutManager = FlexboxLayoutManager(requireContext()).apply {
                    justifyContent = JustifyContent.CENTER
                }
                submitList(RuneData.getInstance().runes.filter {
                    it.type == Rune.Type.RESOLVE
                })
                addItemDecoration(gridSpacingItemDecoration)
            }
        }
    }

    private fun initInspirationRecyclerView() {
        with(binding.inspirationRecyclerView) {
            adapter = RuneAdapter().apply {
                onClick = this@RuneFragment.onClick
                layoutManager = FlexboxLayoutManager(requireContext()).apply {
                    justifyContent = JustifyContent.CENTER
                }
                submitList(RuneData.getInstance().runes.filter {
                    it.type == Rune.Type.INSPIRATION
                })
                addItemDecoration(gridSpacingItemDecoration)
            }
        }
    }
}