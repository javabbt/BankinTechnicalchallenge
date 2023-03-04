package com.bankin.challenge.feature.album.data.datasource.api.model

import androidx.room.TypeConverter
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Parent(
    val id: Int?,
    @SerialName("resource_type") val resourceType: String?,
    @SerialName("resource_uri") val resourceUri: String?
)

class ParentTypeConverter {

    @TypeConverter
    fun fromParent(parent: Parent): String = buildString {
        append(parent.id)
        append("$")
        append(parent.resourceType)
        append("$")
        append(parent.resourceUri)
    }

    @TypeConverter
    fun toParent(value: String): Parent{
        val parent = value.split("$")
        return Parent(
            id = parent[0].toInt(),
            resourceType = parent[1],
            resourceUri = parent[2]
        )
    }

}