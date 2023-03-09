package com.example.campusoffer.adapters

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.campusoffer.databinding.ProductRowBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.ImageListDiffUtil
import com.example.campusoffer.util.ProductListDiffUtil

class ProductsAdapter:RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private var productList = emptyList<Product>()
    private var imagesList = emptyList<Bitmap?>()
    private val TAG = "ProductsAdapter"

    class MyViewHolder(private val binding: ProductRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, currentCover: Bitmap?){
            binding.product = product
            binding.coverImage = currentCover
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = productList[position]
        val currentCover = imagesList[position]
        holder.bind(currentProduct, currentCover)
    }

    fun setProductsData(newDataProducts: List<Product>){
        val productsDiff =
            ProductListDiffUtil(productList, newDataProducts)
        val diffUtilResultProducts = DiffUtil.calculateDiff(productsDiff)
        productList = newDataProducts
        diffUtilResultProducts.dispatchUpdatesTo(this)

    }

    fun setCoverImagesData(newDataCoverImages : List<Bitmap?>){
        Log.v(TAG, "setCoverImagesData")
//        val imagesDiff = ImageListDiffUtil(imagesList, newDataCoverImages)
//        val diffUtilResultImages = DiffUtil.calculateDiff(imagesDiff)
        imagesList = newDataCoverImages
//        diffUtilResultImages.dispatchUpdatesTo(this)
    }
}