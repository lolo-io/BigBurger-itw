package com.loicteyssierdev.bigburger.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.loicteyssierdev.bigburger.databinding.FragmentProductsBinding
import com.loicteyssierdev.bigburger.model.Product
import com.loicteyssierdev.bigburger.ui.cart.CartViewModel

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null

    private val productViewModel by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]
    }

    private val cartViewModel by activityViewModels<CartViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding
            ?: throw IllegalAccessException(
                "_binding is only valid between " +
                        "onCreateView and onDestroyView."
            )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        binding.mainRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.mainRecyclerView.adapter = ProductsAdapter(
            productViewModel.products.value
                ?: arrayListOf(),
            cartViewModel.productsInCart.value ?: arrayListOf()
        ) { product ->
            onClickBuyProduct(product)
        }

        productViewModel.fetchProducts()

        productViewModel.products.observe(viewLifecycleOwner) {
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(
                ProductsFragmentDirections.actionProductsToCart()
            )
        }

        return binding.root
    }

    private fun onClickBuyProduct(product: Product) {
        cartViewModel.addProductInCart(product)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}