package com.example.campusoffer.ui.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ImagesAdapter
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.databinding.FragmentImagesBinding
import com.example.campusoffer.databinding.FragmentOverviewBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.viewmodels.ImagesViewModel
import com.example.campusoffer.viewmodels.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.campusoffer.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_overview.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OverviewFragment : Fragment() {

    lateinit var userViewModel: UserViewModel


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

        return mView
    }

    private fun requestCoverImage(){
        if(myBundle._images.isNullOrEmpty()){
            Toast.makeText(
                requireContext(),
                "No Images Available",
                Toast.LENGTH_SHORT
            ).show()
            return


        view.detail_title.text = myBundle?.title
        view.detail_price.text = myBundle?.price.toString()
        view.detail_description.text = myBundle?.description
        val imageBytes = myBundle?.coverImage
        if (imageBytes != null){
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            view.detail_image.setImageBitmap(decodedImage)
        }

        overviewViewModel.requestCoverImage(myBundle!!)
        overviewViewModel.imageBitmap.observe(viewLifecycleOwner) {bitmap ->
            when(bitmap){
                null -> {}
                else -> {
                    mView.detail_image.setImageBitmap(bitmap)
                }
            }
        }
        // TODO query user information via product id maybe?
        userViewModel = UserViewModel()
        val user = userViewModel.applyHardCodeData()
        view.detail_seller_first_name.text = user.firstName
        view.detail_seller_last_name.text = user.lastName
        view.detail_seller_bio.text = user.bio

        return view
    }

}