package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusoffer.R
import com.example.campusoffer.adapters.FavoritesAdapter
import com.example.campusoffer.databinding.FragmentFavoriteBinding
import com.example.campusoffer.viewmodels.FavoriteViewModel



class FavoriteFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private val mAdapter by lazy { FavoritesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel = FavoriteViewModel();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        mView = binding.root
        setupRecyclerView()
        requestData()

        return mView
    }

    private fun requestData() {
        val productListData = favoriteViewModel.applyHardCodeData()
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