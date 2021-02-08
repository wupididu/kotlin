package com.androiddevs.shopinglist.data.repositories

import com.androiddevs.shopinglist.data.db.ShoppingDatabase
import com.androiddevs.shopinglist.data.db.entities.ShoppingItem

class ShoppingRepositories (
    private val db: ShoppingDatabase
        ){
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}