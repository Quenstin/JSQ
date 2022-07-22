package com.example.calculator.service

import com.example.calculator.data.TestBean
import com.hdyj.basicmodel.service.homeUrl
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by hdyjzgq
 * data on 4/15/21
 * function is ：
 */
interface CalculatorApi {
    @FormUrlEncoded
    @POST(homeUrl)
    suspend fun homeApi(@Field("nianji") nianji: String): TestBean
}