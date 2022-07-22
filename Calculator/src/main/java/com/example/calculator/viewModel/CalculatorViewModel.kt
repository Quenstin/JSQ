package com.example.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.ExpressionHandler.Constants
import com.example.calculator.ExpressionHandler.ExpressionHandler
import com.example.calculator.repository.TestRepository
import com.hdyj.basicmodel.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by hdyjzgq
 * data on 2021/3/15
 * function is ：计算viewModel
 */
class CalculatorViewModel : BaseViewModel<TestRepository>() {

    val result = MutableLiveData<String>()


    fun setFormula(string: String) {
        viewModelScope.launch {
            val calculation = ExpressionHandler.calculation(string)
            if (calculation[1] == "true") {
                result.value = "= ${calculation[0]}"
            } else {
                Constants.setAns(calculation[0])

                result.value = "= ${calculation[0]}"
//                if (calculation[0].length > 1000) {
//
//                } else {
//                    result.value = "= ${calculation[0]}"
//
//                }
            }

        }
    }
}