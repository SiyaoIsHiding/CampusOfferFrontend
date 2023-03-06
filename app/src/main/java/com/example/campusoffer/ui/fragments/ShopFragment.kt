package com.example.campusoffer.ui.fragments
import android.os.Bundle
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
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.Constants
import com.example.campusoffer.viewmodels.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Reference
 * https://developer.android.com/topic/libraries/view-binding
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ShopFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var shopViewModel: ShopViewModel
    private val mAdapter by lazy { ProductsAdapter() }
    private lateinit var mView: View

    private val TAG = "ShopFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
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
        val queryMap = HashMap<String, String>()
        queryMap.put(Constants.QUERY_CATEGORY_ID, Constants.CATEGORY_ROOT_ID) //TODO: hard code category id
        shopViewModel.getProductsList(queryMap)
        shopViewModel.productsList.observe(viewLifecycleOwner ){response ->
            when(response){
                emptyList<Product>() -> {
                    showShimmerEffect()
                }
                else -> {
                    mAdapter.setData(response as List<Product>)
                    hideShimmerEffect()
                }
            }


        }
//        shopViewModel.getProductsUnderCategory(queryMap);
//        shopViewModel.productsUnderCategoryRes.observe(viewLifecycleOwner) { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    Log.v(TAG, response.data?.productId.toString())
//                }
//                is NetworkResult.Error -> {
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                is NetworkResult.Loading -> {
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
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

