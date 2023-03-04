package com.bankin.challenge.feature.album.data.datasource.api.model

data class Category(
    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: Parent,
    val resource_type: String,
    val resource_uri: String
)