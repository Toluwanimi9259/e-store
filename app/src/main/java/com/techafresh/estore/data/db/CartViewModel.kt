package com.techafresh.estore.data.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techafresh.estore.data.db.model.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    fun insert(item: CartItem) =
        GlobalScope.launch {
            repository.insert(item)
        }

    fun delete(item: CartItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun insertHistory(historyItem: HistoryItem) = GlobalScope.launch {
        repository.insertHistory(historyItem)
    }

    fun deleteHistory(historyItem: HistoryItem) = GlobalScope.launch {
        repository.deleteHistory(historyItem)
    }

    fun deleteAllHistory() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getFullHistory() = repository.getAllHistory()

    fun clearCart() = viewModelScope.launch(IO) {
        repository.clearCart()
    }

}