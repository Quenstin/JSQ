package com.example.calculator.repository

import com.example.calculator.service.CalculatorApi
import com.hdyj.basicmodel.http.RetrofitFactory
import com.hdyj.basicmodel.repository.BaseRepository

/**
 * Created by hdyjzgq
 * data on 3/29/21
 * function is ：
 */
abstract class ApiRepository : BaseRepository(){

    protected val apiService: CalculatorApi by lazy {
        RetrofitFactory.instance.create(CalculatorApi::class.java)

    }
}