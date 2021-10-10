package com.shopshop.view

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.*
import com.shopshop.R
import com.shopshop.view.MainAdapter.*
import com.shopshop.databinding.ListItemBinding
import com.shopshop.model.ItemResponse

class MainAdapter : ListAdapter<ItemResponse, ItemViewHolder>(DiffItem()) {

    class DiffItem : DiffUtil.ItemCallback<ItemResponse>() {
        override fun areItemsTheSame(oldItem: ItemResponse, newItem: ItemResponse) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ItemResponse, newItem: ItemResponse) =
            oldItem == newItem
    }

    inner class ItemViewHolder(val view: ListItemBinding) : ViewHolder(view.root) {
        fun bind(item: ItemResponse) {
            with(view) {
                itemTvTitle.text = item.title
                itemTvDesc.text = item.desc
                itemTvDate.text = item.data.toString()

                val layerDrawable: LayerDrawable =
                    ContextCompat.getDrawable(root.context, R.drawable.bottom_type) as LayerDrawable

                val gradientDrawable:GradientDrawable = layerDrawable.findDrawableByLayerId(R.id.main_drawable) as GradientDrawable

                gradientDrawable.setStroke(4,item.type)
                itemContainer.background = layerDrawable

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
