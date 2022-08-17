package com.loicteyssierdev.bigburger.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loicteyssierdev.bigburger.databinding.ProductIncartBinding
import com.loicteyssierdev.bigburger.model.Product
import com.loicteyssierdev.bigburger.model.priceInEuro

class CartAdapter(
    private val productsInCart: List<Product>,
    val onClickRemoveProduct: (Product) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    companion object {
        const val TAG = "ItemsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemBinding =
            ProductIncartBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        return CartViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        try {
            productsInCart[position].let {
                holder.bind(it)
            }
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "items[${position}] does not exist")
        }
    }

    override fun getItemCount(): Int {
        return productsInCart.size
    }

    inner class CartViewHolder(private val binding: ProductIncartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.title.text = product.title
            binding.price.text = product.priceInEuro
            Glide.with(binding.root.context)
                .load(product.thumbnail)
                .into(binding.thumbnail)

            binding.remove.setOnClickListener {
                onClickRemoveProduct(product)
            }
        }
    }
}