package com.eshopthis.finds.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eshopthis.finds.models.Item

@Dao
interface ItemDAO {
    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT COUNT(*) FROM items WHERE name = :itemName")
    suspend fun isTitleExists(itemName: String): Int

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int)
}