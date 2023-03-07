package com.example.campusoffer.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.campusoffer.R
import com.example.campusoffer.databinding.ImageRowBinding



class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    private var imagesRrcList = emptyList<Bitmap>()

    class MyViewHolder(private val binding: ImageRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageRrc : Bitmap){
            val imageView: ImageView = itemView.findViewById(R.id.images)
            imageView.setImageBitmap(imageRrc)
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesAdapter.MyViewHolder {
        return ImagesAdapter.MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = imagesRrcList[position]
        holder.bind(currentImage)
    }

    override fun getItemCount(): Int {
        return imagesRrcList.size
    }


    fun setData(newData: List<Bitmap>){
        // Diff Utils
        imagesRrcList = newData
    }
}