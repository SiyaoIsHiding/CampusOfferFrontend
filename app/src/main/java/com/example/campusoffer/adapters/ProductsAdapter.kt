package com.example.campusoffer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.campusoffer.databinding.ProductRowBinding
import com.example.campusoffer.models.Product
import com.example.campusoffer.util.ProductListDiffUtil

class ProductsAdapter:RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private var productList = emptyList<Product>()

    class MyViewHolder(private val binding: ProductRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product : Product){
            binding.product = product
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
        val currentProduct = productList[position] ?: return
        holder.bind(currentProduct)
    }

    fun setData(newData: List<Product>){
        val recipesDiffUtil =
            ProductListDiffUtil(productList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        productList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}