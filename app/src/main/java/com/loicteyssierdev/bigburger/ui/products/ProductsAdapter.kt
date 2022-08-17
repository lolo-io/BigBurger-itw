package com.loicteyssierdev.bigburger.ui.products

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loicteyssierdev.bigburger.R
import com.loicteyssierdev.bigburger.databinding.ProductCardBinding
import com.loicteyssierdev.bigburger.model.Product
import com.loicteyssierdev.bigburger.model.priceInEuro

class ProductsAdapter(
    private val products: List<Product>,
    private val inCartProducts: List<Product>,
    val onClickBuyProduct: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ItemsViewHolder>() {

    companion object {
        const val TAG = "ItemsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemBinding =
            ProductCardBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
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
        fun bind(product: Product) {
            binding.title.text = product.title
            binding.description.text = product.description
            binding.price.text = product.priceInEuro
            Glide.with(binding.root.context)
                .load(product.thumbnail)
                .into(binding.thumbnail)

            binding.buy.setColorFilter(
                if (inCartProducts.contains(product))
                    Color.RED
                else
                    binding.root.context.applicationContext.getColor(R.color.green)

            )

            binding.buy.setOnClickListener {
                onClickBuyProduct(product)
            }
        }
    }
}