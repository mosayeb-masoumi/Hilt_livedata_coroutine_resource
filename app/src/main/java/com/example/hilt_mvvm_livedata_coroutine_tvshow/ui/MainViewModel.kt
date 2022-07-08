package com.example.hilt_mvvm_livedata_coroutine_tvshow.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.bersyte.tvshowapp.models.UserItemModel
import com.example.hilt_mvvm_livedata_coroutine_tvshow.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){


    private val _response = MutableLiveData<Resource<List<UserItemModel>>>()
    val getTvShowList: MutableLiveData<Resource<List<UserItemModel>>> get() = _response


//    private val _response = mutableStateOf(UserListState())
//    val getTvShowList : State<UserListState> = _response


    init {
        getAllTvShows()
        _response.value = Resource.Loading()
    }

    private fun getAllTvShows() = viewModelScope.launch {

        try {

            mainRepository.getShowTvList().let { response ->

                if (response.isSuccessful) {
//                  _response.value = UserListState(userList = response.body())
                    _response.value = Resource.Success(response.body())


                }else{
                    _response.value = Resource.Error( response.message(), null)
                }

            }

        }catch (e: HttpException){
            _response.value = Resource.Error(e.localizedMessage ?: "an unexpected error occured", null)
        }catch (e: IOException){
            _response.value = Resource.Error( "check your internet connection", null)
        }




    }


}