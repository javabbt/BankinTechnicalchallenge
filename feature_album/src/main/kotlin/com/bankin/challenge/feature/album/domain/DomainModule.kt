package com.bankin.challenge.feature.album.domain

import com.bankin.challenge.feature.album.domain.usecase.GetCategoriesListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {
    singleOf(::GetCategoriesListUseCase)
}
