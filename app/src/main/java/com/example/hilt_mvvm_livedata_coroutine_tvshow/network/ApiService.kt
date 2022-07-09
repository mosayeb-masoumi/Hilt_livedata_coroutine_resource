package com.example.hilt_mvvm_livedata_coroutine_tvshow.network

import com.bersyte.tvshowapp.models.UserItemModel
import com.example.hilt_mvvm_livedata_coroutine_tvshow.helper.Constants
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import java.util.ArrayList
import retrofit2.Call

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getTvShows(): Response<List<UserItemModel>>


    @GET(Constants.END_POINT)
    fun refreshToken(): Call<JsonObject?>?
}