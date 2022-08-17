package com.loicteyssierdev.bigburger.ui.products

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loicteyssierdev.bigburger.databinding.ProductCardBinding
import com.loicteyssierdev.bigburger.model.Product

class ProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ItemsViewHolder>() {

    companion object {
        const val TAG = "ItemsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemBinding =
            ProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        try {
            products[position].let {
                holder.bind(it)
            }
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "items[${position}] does not exist")
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ItemsViewHolder(private val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Product) {
            binding.title.text = post.title
            //binding.root.setOnClickListener {}
        }
    }
}