package com.example.rssreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String> = _searchText

    fun updateSearchText(text: String) {
        _searchText.value = text
    }
}