package com.example.campusoffer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.models.Product
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment() {


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
        view.detail_image.setImageResource(R.drawable.ic_landscape)


        return view
    }

}