package com.loicteyssierdev.bigburger.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.loicteyssierdev.bigburger.R
import com.loicteyssierdev.bigburger.databinding.FragmentProductsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null

    private val homeViewModel by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]
    }

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

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.mainRecyclerView.adapter = ProductsAdapter(
            homeViewModel.products.value
                ?: arrayListOf()
        )

        homeViewModel.fetchProducts()

        homeViewModel.products.observe(viewLifecycleOwner) {
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}