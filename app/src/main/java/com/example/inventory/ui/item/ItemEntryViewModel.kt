/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * View Model to validate and insert items in the Room database.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
//    fun updateUiState(newItemUiState: ItemUiState) {
//        itemUiState = newItemUiState.copy( actionEnabled = newItemUiState.isValid())
//    }
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    fun ItemDetails.isValid() : Boolean {
        return name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
    }

    /** Insert an item into Room database. Adds the data to the database in a non-blocking way */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

//    fun Item.toItemDetails(): ItemDetails = ItemDetails(
//    id = id,
//    name = name,
//    price = price.toString(),
//    quantity = quantity.toString()
//    )
//
//    fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
//    itemDetails = this.toItemDetails(),
//    isEntryValid = isEntryValid
//    )

//    fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
//        itemDetails = this.toItemDetails(),
//        isEntryValid = isEntryValid
//    )

//    fun Item.formatedPrice(): String {
//        return NumberFormat.getCurrencyInstance().format(price)
//    }

//    data class ItemUiState(
//        val itemDetails: ItemDetails = ItemDetails(),
//        val isEntryValid: Boolean = false
//    )

//    data class ItemDetails(
//        val id: Int = 0,
//        val name: String = "",
//        val price: String = "",
//        val quantity: String = "",
//    )
}
