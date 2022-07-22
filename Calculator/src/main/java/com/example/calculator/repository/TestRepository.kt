package com.example.calculator.repository

import com.example.calculator.data.TestBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Created by hdyjzgq
 * data on 3/29/21
 * function is ：请求接口
 */
class TestRepository : ApiRepository() {

    suspend fun getHome(str: String): TestBean {
        return withContext(Dispatchers.IO) {
            apiService.homeApi(str)
        }


    }




}