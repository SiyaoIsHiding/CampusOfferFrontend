package com.example.campusoffer.binding

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.models.Product
import com.example.campusoffer.ui.DetailsActivity
//import com.example.campusoffer.ui.SaleListActivityDirections
import com.example.campusoffer.ui.fragments.FavoriteFragmentDirections
import com.example.campusoffer.ui.fragments.ShopFragmentDirections

class ProductRowBinding{
    companion object{
        val TAG = "ProductRowBinding"

        @BindingAdapter("onProductClickListener")
        @JvmStatic
        fun onProductClickListener(productRowLayout: ConstraintLayout, product: Product) {
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

        @BindingAdapter("onProductClickListenerFavorite")
        @JvmStatic
        fun onProductClickListenerFavorite(favoriteRowLayout: ConstraintLayout, product: Product) {
            favoriteRowLayout.setOnClickListener {
                try {
                    val action =
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailsActivity(product)
                    favoriteRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onProductClickListener", e.toString())
                }
            }
        }


        // Actually use onBindViewHolder in SellListAdapter
        @BindingAdapter("onProductClickListenerSell")
        @JvmStatic
        fun onProductClickListenerSell(sellRowLayout: ConstraintLayout, product: Product) {
            sellRowLayout.setOnClickListener {
                try {
                    sellRowLayout.findNavController().navigate(R.id.detailsActivity)
                } catch (e: Exception) {
                    Log.d("onProductClickListener", e.toString())
                }
            }
        }


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, drawable: Drawable?) {
            if (drawable == null) {
                imageView.setImageResource(R.drawable.ic_no_image)
            }
            imageView.setImageDrawable(drawable)
        }



        @BindingAdapter("applySoldColor")
        @JvmStatic
        fun applySoldColor(view: View, sold: Int) {
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