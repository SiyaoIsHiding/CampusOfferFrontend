package com.example.campusoffer.ui.fragments

import android.graphics.Bitmap
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ImagesFragment : Fragment() {


    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var imagesViewModel: ImagesViewModel
    private val mAdapter by lazy { ImagesAdapter() }
    private lateinit var mView: View
    private lateinit var myBundle: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagesViewModel = ViewModelProvider(requireActivity()).get(ImagesViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        myBundle = args?.getParcelable("productBundle")!!

        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        mView = binding.root
        setupRecyclerView()
        requestData()


        return mView
    }

    private fun requestData() {
        imagesViewModel.requestImage(myBundle!!)
        imagesViewModel.imageBitmapList.observe(viewLifecycleOwner) {response ->
            when(response){
                emptyList<Bitmap>() -> {
                    showShimmerEffect()
                }
                else -> {
                    mAdapter.setData(response as List<Bitmap>)
                    hideShimmerEffect()
                }
            }
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