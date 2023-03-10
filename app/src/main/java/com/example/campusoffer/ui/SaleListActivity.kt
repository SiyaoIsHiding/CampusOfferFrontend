package com.example.campusoffer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campusoffer.R
import com.example.campusoffer.adapters.FavoritesAdapter
import com.example.campusoffer.adapters.SellListAdapter
import com.example.campusoffer.databinding.ActivitySaleListBinding
import com.example.campusoffer.databinding.FragmentFavoriteBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.viewmodels.FavoriteViewModel
import com.example.campusoffer.viewmodels.SellListViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class SaleListActivity : AppCompatActivity() {
    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: ActivitySaleListBinding? = null
    private val binding get() = _binding!!
    private lateinit var sellListViewModel: SellListViewModel
    private val mAdapter by lazy { SellListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_list)
        val shimmerRecyclerView = findViewById<ShimmerRecyclerView>(R.id.shimmer_recycler_view)
        shimmerRecyclerView.layoutManager = LinearLayoutManager(this)
        shimmerRecyclerView.showShimmer()
        requestData(shimmerRecyclerView)

    }



    private fun requestData(shimmerRecyclerView: ShimmerRecyclerView) {
        sellListViewModel = SellListViewModel()
        shimmerRecyclerView.adapter = mAdapter
        val sellList = sellListViewModel.applyHardCodeData()
        Log.d("sellList", sellList.toString())
        if( sellList != null) {
            shimmerRecyclerView.hideShimmer()
            mAdapter.setData(sellList)
        } else {
            shimmerRecyclerView.showShimmer()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}