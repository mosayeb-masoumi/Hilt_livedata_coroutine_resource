package com.example.hilt_mvvm_livedata_coroutine_tvshow.ui

import androidx.lifecycle.*
import com.bersyte.tvshowapp.models.UserItemModel
import com.example.hilt_mvvm_livedata_coroutine_tvshow.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _responseList = MutableLiveData<Resource<List<UserItemModel>>>()
    val getTvShowList: MutableLiveData<Resource<List<UserItemModel>>> get() = _responseList
    val getItemLiveData: MutableLiveData<Resource<UserItemModel>> = MutableLiveData<Resource<UserItemModel>>()


    init {
        getAllTvShows()
        _responseList.value = Resource.Loading()
    }


    private fun getAllTvShows() = viewModelScope.launch {
        try {
            mainRepository.getShowTvList().let { response ->
                if (response.isSuccessful) {
                    _responseList.value = Resource.Success(response.body())
                } else {
                    _responseList.value = Resource.Error(response.message(), null)
                }
            }
        } catch (e: HttpException) {
            _responseList.value =
                Resource.Error(e.localizedMessage ?: "an unexpected error occured", null)
        } catch (e: IOException) {
            _responseList.value = Resource.Error("check your internet connection", null)
        }
    }


    fun getItem(user_id: Int): LiveData<Resource<UserItemModel>?> {


        getItemLiveData.value = Resource.Loading()  // to show loading when click on button

        viewModelScope.launch {
            try {
                mainRepository.getShowTvList().let { response ->
                    if (response.isSuccessful) {
                        getItemLiveData.value = Resource.Success(response.body()?.get(user_id))
                    } else {
                        getItemLiveData.value = Resource.Error(response.message(), null)
                    }
                }
            } catch (e: HttpException) {
                getItemLiveData.value = Resource.Error(e.localizedMessage ?: "an unexpected error occured", null)
            } catch (e: IOException) {
                getItemLiveData.value = Resource.Error("check your internet connection", null)
            }
        }

        return getItemLiveData
    }




    public override fun onCleared() {
        getItemLiveData.value = null
    }

}

