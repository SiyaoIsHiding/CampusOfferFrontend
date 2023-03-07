package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ImagesAdapter
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.databinding.FragmentImagesBinding
import com.example.campusoffer.databinding.FragmentShopBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.Constants
import com.example.campusoffer.viewmodels.FavoriteViewModel
import com.example.campusoffer.viewmodels.ImagesViewModel
import com.example.campusoffer.viewmodels.ShopViewModel


class ImagesFragment : Fragment() {


    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var imagesViewModel: ImagesViewModel
    private val mAdapter by lazy { ImagesAdapter() }
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        imagesViewModel = ImagesViewModel();
        inflater.inflate(R.layout.fragment_images, container, false)
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        mView = binding.root
        setupRecyclerView()
        requestData()

        return mView
    }

    private fun requestData() {
        val imageSrcList = imagesViewModel.applyHardCodeData()
        if( imageSrcList != null) {
            hideShimmerEffect()
            mAdapter.setData(imageSrcList)
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