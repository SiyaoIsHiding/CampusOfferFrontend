package com.example.campusoffer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusoffer.R
import com.example.campusoffer.adapters.SellListAdapter
import com.example.campusoffer.databinding.ActivitySaleListBinding
import com.example.campusoffer.util.Constants
import com.example.campusoffer.viewmodels.SellListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SaleListActivity : AppCompatActivity() {
    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: ActivitySaleListBinding? = null
    private val binding get() = _binding!!
    private lateinit var sellListViewModel: SellListViewModel
    private val mAdapter by lazy { SellListAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellListViewModel = ViewModelProvider(this).get(SellListViewModel::class.java)
//        setContentView(R.layout.activity_sale_list)
//        val shimmerRec                        yclerView = findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view)
//        shimmerRecyclerView.layoutManager = LinearLayoutManager(this)
//        shimmerR                                           )
//        requestData(shimmerRecyclerView)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_sale_list)
        setupRecyclerView()
        requestData()
    }


    private fun requestData() {
        val queryMap = HashMap<String, String>()
        queryMap.put(Constants.QUERY_USER_ID, Constants.USER_TEST_ID)
        sellListViewModel.getProductsList(queryMap)
        sellListViewModel.productsList.observe(this ){response ->
            when(response){
                null -> {
                    showShimmerEffect()
                }
                else -> {
                    mAdapter.setProductsData(response.filterNotNull())
                    hideShimmerEffect()
                }
            }
        }
        sellListViewModel.coverImageList.observe(this){imageList ->
            mAdapter.setCoverImagesData(imageList)
        }
    }

    private fun setupRecyclerView() {
        binding!!.shimmerRecyclerView.adapter = mAdapter
        binding!!.shimmerRecyclerView.layoutManager = LinearLayoutManager(this)
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