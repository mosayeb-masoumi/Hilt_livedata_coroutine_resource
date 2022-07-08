package com.example.hilt_mvvm_livedata_coroutine_tvshow.ui

import com.example.hilt_mvvm_livedata_coroutine_tvshow.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getShowTvList() = apiService.getTvShows()

}

