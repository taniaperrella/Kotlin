package com.wcc.whatdoilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcc.whatdoilearn.data.LearnedItemDao
import com.wcc.whatdoilearn.entities.LearnedItem
import com.wcc.whatdoilearn.repository.LearnedItemsRepository
import kotlinx.coroutines.launch

class LearnedItemViewModel(private var repository: LearnedItemsRepository) : ViewModel() {
    val learnedItems: LiveData<List<LearnedItem>>

    init {
        learnedItems = repository.learnedItems
    }

    fun insertNewLearnedItem(item: LearnedItem) {
        viewModelScope.launch {
            repository.insertNewLearnedItem(item)
        }
    }

}