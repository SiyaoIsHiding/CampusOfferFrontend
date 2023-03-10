package com.example.campusoffer.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.campusoffer.databinding.FavoriteProductRowBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.ImageListDiffUtil
import com.example.campusoffer.util.ProductListDiffUtil

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {

    private var productList = emptyList<Product>()
    private var imagesList = emptyList<Drawable?>()
    private lateinit var starImage: ImageView

    class MyViewHolder(private val binding: FavoriteProductRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product : Product, currentCover: Drawable?){
            binding.product = product
            binding.coverImage = currentCover
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteProductRowBinding.inflate(layoutInflater, parent, false)
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

    fun setProductData(newData : List<Product>){
        val recipesDiffUtil =
            ProductListDiffUtil(productList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        productList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun setCoverImagesData(newDataCoverImages: List<Drawable?>){
        val imagesDiff = ImageListDiffUtil(imagesList, newDataCoverImages)
        val diffUtilResultImages = DiffUtil.calculateDiff(imagesDiff)
        imagesList = newDataCoverImages
        diffUtilResultImages.dispatchUpdatesTo(this)
    }
}