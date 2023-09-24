package com.sriram.countries.util

import androidx.recyclerview.widget.DiffUtil
import com.sriram.countries.domain.model.CountryListItem

class CountryDiffUtil(
    private val oldList: List<CountryListItem>,
    private val newList: List<CountryListItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, value, name) = oldList[oldItemPosition]
        val (_, value1, name1) = newList[newItemPosition]
        return name == name1 && value == value1
    }
}
