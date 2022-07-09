package com.example.hilt_mvvm_livedata_coroutine_tvshow.di

import com.example.hilt_mvvm_livedata_coroutine_tvshow.helper.Constants
import com.example.hilt_mvvm_livedata_coroutine_tvshow.network.ApiService
import com.example.hilt_mvvm_livedata_coroutine_tvshow.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL



    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient().buildService(ApiService::class.java)
    }


//    @Provides
//    @Singleton
//    fun provideRetrofitInstance(BASE_URL: String): ApiService =
//
//        RetrofitClient().buildService(ApiService)
////        Retrofit.Builder()
////            .baseUrl(BASE_URL)
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////            .create(ApiService::class.java)
}

