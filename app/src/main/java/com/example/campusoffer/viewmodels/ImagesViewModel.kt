package com.example.campusoffer.viewmodels

import androidx.lifecycle.AndroidViewModel
import com.example.campusoffer.R
import com.example.campusoffer.models.Product

class ImagesViewModel {

    private var imageRrcList : MutableList<Int> = mutableListOf()

    fun applyHardCodeData() : List<Int> {

        var image1 = R.drawable.ic_landscape
        var image2 = R.drawable.ic_landscape2
        imageRrcList.add(image1)
        imageRrcList.add(image2)
        return imageRrcList
        }

}