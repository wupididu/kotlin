package com.androiddevs.shopinglist.ui.shoppinglist

import com.androiddevs.shopinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}