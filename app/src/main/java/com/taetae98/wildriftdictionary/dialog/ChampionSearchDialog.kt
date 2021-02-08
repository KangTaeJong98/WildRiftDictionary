package com.taetae98.wildriftdictionary.dialog

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ChampionAdapter
import com.taetae98.wildriftdictionary.base.BaseDialog
import com.taetae98.wildriftdictionary.data.ChampionData
import com.taetae98.wildriftdictionary.databinding.DialogChampionSearchBinding
import java.util.*

class ChampionSearchDialog(context: Context) : BaseDialog<DialogChampionSearchBinding>(context, R.layout.dialog_champion_search) {
    private val championAdapter by lazy { ChampionAdapter().apply { submitList(ChampionData.getInstance().champions.values.toMutableList().apply { sortBy { it.nameLocale } }) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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