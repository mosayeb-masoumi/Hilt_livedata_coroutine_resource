package com.example.hilt_mvvm_livedata_coroutine_tvshow.ui

import com.bersyte.tvshowapp.models.UserItemModel

data class UserListState(
    val isLoading: Boolean = false,
    val userList: List<UserItemModel>? = emptyList() ,
    val error : String = ""

)
