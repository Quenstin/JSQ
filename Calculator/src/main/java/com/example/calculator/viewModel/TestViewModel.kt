package com.example.calculator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.calculator.data.TestBean
import com.example.calculator.repository.TestRepository
import com.hdyj.basicmodel.ext.simpleLaunch
import com.hdyj.basicmodel.viewmodel.BaseViewModel
import io.reactivex.Emitter
import java.io.FileInputStream

/**
 * Created by hdyjzgq
 * data on 3/29/21
 * function is ：测试类
 */
class TestViewModel : BaseViewModel<TestRepository>() {
    private val data = MutableLiveData<TestBean>()
    val mData : LiveData<TestBean> = data

    fun getHomeData(nianji: String) {
        simpleLaunch({
            data.value = mRepository.getHome(nianji)
        })

    }
}