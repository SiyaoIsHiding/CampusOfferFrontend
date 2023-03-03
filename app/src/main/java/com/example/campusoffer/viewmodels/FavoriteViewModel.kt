package com.example.campusoffer.viewmodels

import com.example.campusoffer.models.Product

class FavoriteViewModel {

    fun applyHardCodeData() : List<Product> {

        val image = listOf<String>(
            "b8884ed5-b0de-11ed-a0a9-00224829ee55",
            "c514d4b9-b0de-11ed-a0a9-00224829ee55"
        )
        val product1: Product = Product(
            "41859207-5471-4223-b01c-e566d506c799",
            "0301",
            "A fully working chair. Bought in March last year.",
            "92c6ebb6-b0ca-11ed-a0a9-00224829ee55",
            image,
            1,
            29.9,
            "fcda1dda-5b3b-4c6c-88a7-46521d132015",
            "An office chair at Verano Place"
        )
        val product2: Product = Product(
            "41859207-5471-4223-b01c-e566d506c799",
            "0301",
            "A fully working chair. Bought in March last year.",
            "92c6ebb6-b0ca-11ed-a0a9-00224829ee55",
            image,
            0,
            29.9,
            "fcda1dda-5b3b-4c6c-88a7-46521d132015",
            "An office chair at Verano Place"
        )
        return listOf(product1, product2)
    }
}