package com.bankin.challenge.base

import com.bankin.challenge.base.presentation.nav.NavManager
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}
