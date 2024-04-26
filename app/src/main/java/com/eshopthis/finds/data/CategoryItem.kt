package com.eshopthis.finds.data

data class CategoryItem(
        var name: String,
        var imageResourceId: Int // Resource ID for the category image
) {
    // Getters and setters are not needed as Kotlin provides default getters and setters for data classes
}