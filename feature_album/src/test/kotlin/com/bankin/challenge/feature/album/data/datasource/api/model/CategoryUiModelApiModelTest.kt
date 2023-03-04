package com.bankin.challenge.feature.album.data.datasource.api.model

import com.bankin.challenge.feature.album.data.DataFixtures
import com.bankin.challenge.feature.album.domain.model.CategoryUiModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class CategoryUiModelApiModelTest {

    @Test
    fun `data model with full data maps to CategoryDomainModel`() {
        // given
        val cut = DataFixtures.getCategoryApiModel()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo CategoryUiModel(
            name = cut.name,
            id = 0,
            colorRes = 0
        )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getCategoryApiModel()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo CategoryUiModel(
            name = "Yann",
            id = 0,
            colorRes = 0
        )
    }
}
