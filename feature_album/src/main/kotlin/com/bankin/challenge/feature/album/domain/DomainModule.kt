package com.bankin.challenge.feature.album.domain

import com.bankin.challenge.feature.album.domain.usecase.GetCategoriesListUseCase
import com.bankin.challenge.feature.album.domain.usecase.GetSubCategoriesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {
    singleOf(::GetCategoriesListUseCase)
    singleOf(::GetSubCategoriesUseCase)
}
