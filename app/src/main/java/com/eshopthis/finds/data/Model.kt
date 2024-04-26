package com.eshopthis.finds.data

data class Model(
        val id: String,
        val title: String,
        val price: String,
        val description: String,
        val imageUrl: String? = null, // for image URLs
        val imageResId: Int = 0 // for local images (resource IDs)
) {
    // Getters and setters are not needed as Kotlin provides default getters and setters for data classes
}