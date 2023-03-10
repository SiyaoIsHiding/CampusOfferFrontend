package com.example.campusoffer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusoffer.adapters.FavoritesAdapter
import com.example.campusoffer.databinding.FragmentFavoriteBinding
import com.example.campusoffer.util.Constants
import com.example.campusoffer.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private val mAdapter by lazy { FavoritesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)
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
        val queryMap = HashMap<String, String>()
        queryMap.put(
            Constants.QUERY_USR_ID,
            Constants.USER_TEST_ID
        ) //TODO: hard coded category id and using getProductsList
        favoriteViewModel.getProductsList(queryMap)
        favoriteViewModel.favoritesList.observe(viewLifecycleOwner) { response ->
            when (response) {
                null -> {
                    showShimmerEffect()
                }
                else -> {
                    mAdapter.setProductData(response.filterNotNull())
                    hideShimmerEffect()
                }
            }
        }
        favoriteViewModel.coverImageList.observe(viewLifecycleOwner) {imagesList ->
            mAdapter.setCoverImagesData(imagesList)
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