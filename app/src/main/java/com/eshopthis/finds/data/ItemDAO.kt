package com.eshopthis.finds.data

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDAO {
    @Insert
    suspend fun insertItem(item: Item) {
        Log.d("ItemDAO", "Inserting item: $item")
    }

    @Query("SELECT COUNT(*) FROM items WHERE name = :itemName")
    suspend fun isTitleExists(itemName: String): Int {
        Log.d("ItemDAO", "Checking if title exists: $itemName")
        return 0 // Placeholder, replace with actual implementation
    }

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item> {
        Log.d("ItemDAO", "Getting all items")
        return emptyList() // Placeholder, replace with actual implementation
    }

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int) {
        Log.d("ItemDAO", "Deleting item with id: $itemId")
    }
}