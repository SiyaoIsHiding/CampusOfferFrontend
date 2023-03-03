package com.example.campusoffer.binding

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.models.Product
import com.example.campusoffer.ui.fragments.ShopFragmentDirections

class ProductRowBinding {

    companion object {

        @BindingAdapter("onProductClickListener")
        @JvmStatic
        fun onProductClickListener(productRowLayout: ConstraintLayout, product: Product) {
            Log.d("onProductClickListener", "CALLED")
            productRowLayout.setOnClickListener {
                try {
                    val action =
                        ShopFragmentDirections.actionShopFragmentToDetailsActivity(product)
                    productRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onProductClickListener", e.toString())
                }
            }
        }

//        @BindingAdapter("loadImageFromUrl")
//        @JvmStatic
//        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
//            imageView.load(imageUrl) {
//                crossfade(600)
//            }
//        }



        @BindingAdapter("applySoldColor")
        @JvmStatic
        fun applyVeganColor(view: View, sold: Int) {
            if(sold == 0){
                when(view){
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            } else {
                when(view){
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.red
                            )
                        )
                    }
                }
            }
        }

    }
}