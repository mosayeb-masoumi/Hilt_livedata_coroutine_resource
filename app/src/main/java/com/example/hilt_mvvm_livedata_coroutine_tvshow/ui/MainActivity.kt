package com.example.hilt_mvvm_livedata_coroutine_tvshow.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.bersyte.tvshowapp.models.UserItemModel
import com.example.hilt_mvvm_livedata_coroutine_tvshow.R
import com.example.hilt_mvvm_livedata_coroutine_tvshow.resource.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels()
//    @Inject
//    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_get_data.setOnClickListener {

            mainViewModel.getTvShowList.observe(this) {
                if (it.status == Resource.Status.SUCCESS) {
                    mainViewModel.getTvShowList.removeObservers(this)
                    val result = it.data
                    val size = result?.size.toString()
                    txt.text = size + " / " + result?.get(0)?.title

                    mainViewModel.getTvShowList.removeObservers(this)

                } else if (it.status == Resource.Status.ERROR) {
                    txt.text = it.message

                } else if (it.status == Resource.Status.LOADING) {
                    txt.text = "loading..."
                }
            }



        }



        /***************************************** correct implementation*********************/
        var item = -1

        btn_get_item.setOnClickListener {
            item++
            val getItemLiveData = mainViewModel.getItem(item)
            getItemLiveData.observe(this) {


                if(it !=null){
                    if (it.status == Resource.Status.SUCCESS) {
                        val result = it.data
                        txt.text = result?.title
                        getItemLiveData.removeObservers(this)
                        mainViewModel.onCleared()  // to prevent looping

                    } else if (it.status == Resource.Status.ERROR) {
                        txt.text = it.message
                        mainViewModel.onCleared()  // to prevent using cache looping
                    } else if (it.status == Resource.Status.LOADING) {
                        txt.text = "loading..."
                    }
                }
            }

        }


    }
}