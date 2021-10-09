package com.shopshop.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.*
import com.shopshop.model.Item
import com.shopshop.view.MainAdapter.*
import com.shopshop.databinding.ListItemBinding

class MainAdapter : ListAdapter<Item, ItemViewHolder>(DiffItem()) {

    class DiffItem : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }

    inner class ItemViewHolder(val view: ListItemBinding) : ViewHolder(view.root) {
        fun bind(item: Item) {
            with(view) {
                itemTvTitle.text = item.name
                itemTvDesc.text = item.desc
                itemTvDate.text = "28/09/2021"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
