package com.wcc.whatdoilearn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcc.whatdoilearn.data.LearnedItemDao
import com.wcc.whatdoilearn.entities.LearnedItem
import com.wcc.whatdoilearn.entities.UnderstandingLevel
import com.wcc.whatdoilearn.repository.LearnedItemsRepository
import kotlinx.coroutines.launch

class NewLearnedItemViewModel(private var repository: LearnedItemsRepository): ViewModel() {

    fun insertNewLearnedItem(itemTitle: String, itemDescription: String) {
        viewModelScope.launch {
            val item = LearnedItem(itemTitle, itemDescription, UnderstandingLevel.HIGH)
            repository.insertNewLearnedItem(item)
        }
    }
}