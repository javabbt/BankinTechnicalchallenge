package com.bankin.challenge.feature.album.data.datasource.api.service

import com.bankin.challenge.base.data.retrofit.ApiResult
import com.bankin.challenge.feature.album.data.datasource.api.response.SearchCategoryResponse
import retrofit2.http.GET

internal interface CategoryRetrofitService {

    @GET("/bankin-engineering/challenge-android/master/categories.json")
    suspend fun getCategoriesAsync(): ApiResult<SearchCategoryResponse>
}
