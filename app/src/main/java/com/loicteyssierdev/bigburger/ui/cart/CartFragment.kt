package com.loicteyssierdev.bigburger.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.loicteyssierdev.bigburger.databinding.FragmentCartBinding
import com.loicteyssierdev.bigburger.model.Product

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

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

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.cartRecyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.cartRecyclerview.adapter = CartAdapter(
            cartViewModel.productsInCart.value
                ?: arrayListOf(),
        ) { product ->
            onClickRemoveProduct(product)
        }

        cartViewModel.productsInCart.observe(viewLifecycleOwner) {
            binding.cartRecyclerview.adapter?.notifyDataSetChanged()
            updateTotalPrice()
        }

        binding.order.setOnClickListener {
            Toast.makeText(requireContext(), "Not implemented yet", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    private fun onClickRemoveProduct(product: Product) {
        binding.cartRecyclerview.adapter?.notifyItemRemoved(
            cartViewModel.productsInCart.value?.indexOf(product) ?: -1
        )
        cartViewModel.removeProductFromCart(product)
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        binding.totalPrice.visibility =
            if (cartViewModel.productsInCart.value?.isNotEmpty() == true) {
                binding.totalPrice.text = cartViewModel.getTotalPrice()
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}