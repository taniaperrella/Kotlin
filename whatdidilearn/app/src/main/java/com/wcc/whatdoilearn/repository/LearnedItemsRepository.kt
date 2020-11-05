package com.wcc.whatdoilearn.repository

import com.wcc.whatdoilearn.data.LearnedItemDao
import com.wcc.whatdoilearn.entities.LearnedItem

class LearnedItemsRepository(private val dao: LearnedItemDao) {
    val learnedItems = dao.getAll()

    suspend fun insertNewLearnedItem(item: LearnedItem) {
        dao.insert(item)
    }
}