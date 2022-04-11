package com.myapplication.ui.itemList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.databinding.ItemListContentBinding
import com.myapplication.databinding.ItemListSectionBinding
import com.myapplication.network.model.Product
import com.myapplication.util.ContentItem
import com.myapplication.util.RecyclerViewItem
import com.myapplication.util.SectionItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

const val VIEW_TYPE_SECTION = 1
const val VIEW_TYPE_ITEM = 2

@FragmentScoped
class ItemListAdapter @Inject constructor(val clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<RecyclerViewItem>()

    override fun getItemViewType(position: Int): Int {
        if (data[position] is SectionItem) {
            return VIEW_TYPE_SECTION
        }
        return VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is ViewHolderSection && item is SectionItem) {
            holder.bindSection(item)
        }
        if (holder is ViewHolderContent && item is ContentItem) {
            holder.bindContent(item, clickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SECTION) {
            return ViewHolderSection.from(parent)
        }
        return ViewHolderContent.from(parent)
    }

    class ViewHolderSection private constructor(private val binding: ItemListSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSection(item: SectionItem) {
            binding.data = item.apiItemList
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolderSection {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListSectionBinding.inflate(layoutInflater, parent, false)
                return ViewHolderSection(binding)
            }
        }
    }

    class ViewHolderContent private constructor(private val binding: ItemListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindContent(item: ContentItem, clickListener: ClickListener) {
            binding.data = item.product
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolderContent {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolderContent(binding)
            }
        }
    }
}

class ClickListener @Inject constructor() {

    var onItemClick: ((Product) -> Unit)? = null

    fun onClick(product: Product) {
        onItemClick?.invoke(product)
    }

}