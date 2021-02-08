package com.androiddevs.shopinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.androiddevs.shopinglist.data.db.entities.ShoppingItem
import com.androiddevs.shopinglist.data.repositories.ShoppingRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repositories: ShoppingRepositories
) : ViewModel() {
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repositories.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repositories.delete(item)
    }

    fun getAllShoppingItems() = repositories.getAllShoppingItems()
}