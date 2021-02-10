package com.taetae98.wildriftdictionary.dialog

import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ChampionAdapter
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.ChampionData
import com.taetae98.wildriftdictionary.databinding.DialogChampionSearchBinding
import java.util.*

class ChampionSearchDialog : BaseDialog<DialogChampionSearchBinding>(R.layout.dialog_champion_search) {
    private val championAdapter by lazy {
        ChampionAdapter().apply {
            submitList(ChampionData.getInstance().champions.values.toMutableList().apply {
                sortBy { it.nameLocale }
                onClick = {
                    findNavController().navigate(ChampionSearchDialogDirections.actionChampionSearchDialogToChampionInformationFragment(it))
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun init() {
        super.init()
        initTextInputLayout()
        initRecyclerView()
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