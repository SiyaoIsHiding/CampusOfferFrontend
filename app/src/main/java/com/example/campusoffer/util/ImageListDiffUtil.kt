package com.example.campusoffer.util

import android.graphics.Bitmap
import androidx.recyclerview.widget.DiffUtil

class ImageListDiffUtil(
    private val oldList: List<Bitmap?>,
    private val newList: List<Bitmap?>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
//        if (oldList[oldItemPosition] == null && newList[newItemPosition] == null) return true
//        return false
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldList[oldItemPosition] == newList[newItemPosition]
        if (oldList[oldItemPosition] == null && newList[newItemPosition] == null) return true
        return false
    }
}