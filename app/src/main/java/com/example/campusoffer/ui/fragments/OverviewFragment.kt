package com.example.campusoffer.ui.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.models.Product
import com.example.campusoffer.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment() {

    lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments
        val myBundle: Product? = args?.getParcelable("productBundle")



        view.detail_title.text = myBundle?.title
        view.detail_price.text = myBundle?.price.toString()
        view.detail_description.text = myBundle?.description
        val imageBytes = myBundle?.coverImage
        if (imageBytes != null){
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            view.detail_image.setImageBitmap(decodedImage)
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