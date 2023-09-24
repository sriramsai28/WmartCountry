package com.sriram.countries.ui.fragments.datpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sriram.countries.databinding.ListItemBinding
import com.sriram.countries.domain.model.CountryListItem
import com.sriram.countries.util.CountryDiffUtil

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    private val countryList = ArrayList<CountryListItem>()

    // Get the list of countries from Activity.
    fun setCountries(dataSet: List<CountryListItem>) {
        val diffCallBack = CountryDiffUtil(
            oldList = countryList,
            newList = dataSet,
        )
        val diffCountries = DiffUtil.calculateDiff(diffCallBack)
        countryList.clear()
        countryList.addAll(dataSet)
        diffCountries.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        // set data to the view.
        holder.bind(country)
    }

    inner class CountryViewHolder(private val countryBinding: ListItemBinding) :
        RecyclerView.ViewHolder(countryBinding.root) {

        fun bind(countryItem: CountryListItem) {
            countryBinding.apply {
                this.country = countryItem
            }
        }
    }
}
