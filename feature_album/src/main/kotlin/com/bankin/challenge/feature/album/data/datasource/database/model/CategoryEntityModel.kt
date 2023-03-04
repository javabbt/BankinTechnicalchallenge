package com.bankin.challenge.feature.album.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bankin.challenge.feature.album.data.datasource.api.model.Parent
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel

@Entity(tableName = "categories")
internal data class CategoryEntityModel(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val custom: Boolean?,
    val isDeleted: Boolean?,
    val name: String?,
    val other: Boolean?,
    val parent: Parent?,
    val resourceType: String?,
    val resourceUri: String?
)

internal fun CategoryEntityModel.toDomainModel() =
    CategoryUiModel(
        this.name,
        colorRes = 1
    )
