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
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

//import com.example.inventory.ui.item.ItemEntryViewModel.ItemDetails

//var itemUiState by mutableStateOf(ItemUiState())
//    private set

/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
//    val id: Int = 0,
//    val name: String = "",
//    val price: String = "",
//    val quantity: String = "",
//    val actionEnabled: Boolean = false
)

/**
 * Extension function to convert [ItemUiState] to [Item]. If the value of [ItemDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Extension function to convert [ItemUiState] to [Item]. If the value of [ItemUiState.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
//fun Item.toItemUiState(actionEnabled: Boolean = false): ItemUiState = ItemUiState(
//    id = id,
//    name = name,
//    price = price.toString(),
//    quantity = quantity.toString(),
//    actionEnabled = actionEnabled
//)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)

fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)



fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

//fun ItemDetails.isValid() : Boolean {
//    return name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
// }

//fun updateUiState(itemDetails: ItemDetails) {
//    itemUiState = ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
//}
//
////    /** Insert an item into Room database. Adds the data to the database in a non-blocking way */
////    suspend fun saveItem() {
////        if (validateInput()) {
////            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
////        }
////    }
//
//private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
//    return with(uiState) {
//        name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
//    }
//}
//
//suspend fun updateItem(
//    itemsRepository: ItemsRepository
//) {
//    if (validateInput(itemUiState.itemDetails)) {
//        itemsRepository.updateItem(itemUiState.itemDetails.toItem())
//    }
//}
