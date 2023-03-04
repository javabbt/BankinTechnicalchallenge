package com.bankin.challenge.base.presentation.viewmodel

interface BaseAction<State> {
    fun reduce(state: State): State
}
