package com.bankin.challenge.feature.album.domain

import com.bankin.challenge.feature.album.domain.model.CategoryUiModel

object DomainFixtures {

    internal fun getCategory(
        name: String = "albumName",
    ): CategoryUiModel = CategoryUiModel(
        name = name,
        id = 0
    )
}
