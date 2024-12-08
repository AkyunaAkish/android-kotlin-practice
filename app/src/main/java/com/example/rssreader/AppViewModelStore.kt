package com.example.rssreader

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

object AppViewModelStore : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore = ViewModelStore()
}