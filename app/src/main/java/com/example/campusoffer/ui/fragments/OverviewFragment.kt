package com.example.campusoffer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.campusoffer.databinding.FragmentOverviewBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.models.User
import com.example.campusoffer.util.Constants
import com.example.campusoffer.viewmodels.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_overview.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OverviewFragment : Fragment() {


    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var overviewViewModel: OverviewViewModel
    private lateinit var mView: View
    private lateinit var myBundle: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overviewViewModel = ViewModelProvider(requireActivity()).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        myBundle = args?.getParcelable("productBundle")!!

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        mView = binding.root

        mView.detail_title.text = myBundle?.title
        mView.detail_price.text = myBundle?.price.toString()
        mView.detail_description.text = myBundle?.description

        requestCoverImage()
        requestUserData()

        return mView
    }

    private fun requestCoverImage(){
        if(myBundle._images.isNullOrEmpty()) {
            Toast.makeText(
                requireContext(),
                "No Images Available",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        overviewViewModel.requestCoverImage(myBundle!!)
        overviewViewModel.imageBitmap.observe(viewLifecycleOwner) {drawable ->
            when(drawable){
                null -> {}
                else -> {
                    mView.detail_image.setImageDrawable(drawable)
                }
            }
        }
    }

    private fun requestUserData(){
        val queryMap = HashMap<String, String>()
        queryMap.put(Constants.QUERY_ID, Constants.USER_TEST_ID)
        overviewViewModel.getUserProfile(queryMap)
        overviewViewModel.currentUser.observe(viewLifecycleOwner){ user ->
            when(user){
                checkNotNull(user) -> {
                    mView.detail_seller_first_name.text = user.firstName
                    mView.detail_seller_last_name.text = user.lastName
                    mView.detail_seller_bio.text = user.bio
                }
            }

        }
    }

}