package com.bankin.challenge.feature.album.data.datasource.api.model

import com.bankin.challenge.feature.album.data.datasource.database.model.CategoryEntityModel
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Category(
    val custom: Boolean?,
    val id: Int,
    @SerialName("is_deleted") val isDeleted: Boolean?,
    val name: String?,
    val other: Boolean?,
    val parent: Parent?,
    @SerialName("resource_type") val resourceType: String?,
    @SerialName("resource_uri") val resourceUri: String?
)

internal fun Category.toEntityModel() =
    CategoryEntityModel(
        id = this.id,
        custom = this.custom,
        isDeleted = this.isDeleted,
        name = this.name,
        other = this.other,
        parent = this.parent,
        resourceType = this.resourceType,
        resourceUri = this.resourceUri
    )

internal fun Category.toDomainModel() = CategoryUiModel(
    name = this.name, colorRes = 0, id = this.id
)
