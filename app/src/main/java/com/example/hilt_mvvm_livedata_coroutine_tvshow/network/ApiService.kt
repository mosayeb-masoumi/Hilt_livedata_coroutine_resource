package com.example.hilt_mvvm_livedata_coroutine_tvshow.network

import com.bersyte.tvshowapp.models.UserItemModel
import com.example.hilt_mvvm_livedata_coroutine_tvshow.helper.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getTvShows(): Response<List<UserItemModel>>

}