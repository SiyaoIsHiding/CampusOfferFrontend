package com.example.campusoffer.ui.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.databinding.FragmentShopBinding
import com.example.campusoffer.viewmodels.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.view.*

/**
 * Reference
 * https://developer.android.com/topic/libraries/view-binding
 */

class ShopFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var shopViewModel: ShopViewModel
    private val mAdapter by lazy { ProductsAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopViewModel = ShopViewModel();
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        mView = binding.root
        setupRecyclerView()
        requestData()

        binding.productsFilter.setOnClickListener {
            findNavController().navigate(R.id.action_shopFragment_to_filterBottomSheet)
        }
            return mView
    }

    private fun requestData() {
        val productListData = shopViewModel.applyHardCodeData();
        if( productListData != null) {
            hideShimmerEffect()
            mAdapter.setData(productListData)
            Log.d("productList", productListData.toString())
        } else {
            showShimmerEffect()
        }
    }

    private fun setupRecyclerView() {
        binding!!.shimmerRecyclerView.adapter = mAdapter
        binding!!.shimmerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }


    private fun showShimmerEffect() {
        binding.shimmerRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.shimmerRecyclerView.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

