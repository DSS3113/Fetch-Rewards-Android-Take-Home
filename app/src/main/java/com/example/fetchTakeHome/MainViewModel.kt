package com.example.fetchTakeHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainViewModel: ViewModel() {
    val items: MutableStateFlow<List<Item>> =  MutableStateFlow(emptyList())
    private val apiService = RetrofitInstance.getInstance()
    fun getItems() {
        viewModelScope.launch {
            val response: Response<List<Item>> = try {
                apiService.create(ItemApi::class.java).getItems()
            } catch (e: IOException) {
                // Handle errors here
                return@launch
            } catch (e: HttpException) {
                // Handle errors here
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                items.value = response.body()!!
            }
        }
    }
}