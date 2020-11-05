package com.wcc.whatdoilearn.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "learned_item")
data class LearnedItem(
        @ColumnInfo(name = "item_title")
        var tittle: String,
        @ColumnInfo(name = "item_description")
        var description: String,
        @ColumnInfo(name="item_level")
        var understandingLevel: UnderstandingLevel,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "item_id")
        var id: Int = 0
)