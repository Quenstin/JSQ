package com.example.calculator.view

import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.calculator.databinding.ActivityTestBinding
import com.example.calculator.viewModel.TestViewModel
import com.hdyj.basicmodel.utils.hdyjLogd
import com.hdyj.basicmodel.utils.toastShow
import com.hdyj.basicmodel.view.activity.BaseMVVMActivity
import com.hdyj.basicmodel.widget.loading.LineScalePulseOutIndicator
import com.hdyj.commonlibrary.commonwidget.CommonBottomPlayView
import kotlinx.coroutines.flow.*

/**
 * Created by hdyjzgq
 * data on 3/29/21
 * function is ：测试类
 */
class TestActivity : BaseMVVMActivity<TestViewModel, ActivityTestBinding>() {


    override fun initView() {
        mViewBinding.testButton.setOnClickListener {
            mViewModel.getHomeData("oo")

        }
        mViewBinding.loadingIv.setIndicator(LineScalePulseOutIndicator())
        mViewBinding.loadingIv.show()
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            testFlow()
        }
//        mViewModel.getHomeData("12")
//
//        flow<Int> {
//            (0..4).forEach {
//                emit(it)
//            }
//        }


    }

    suspend fun testFlow() {
        flow {
            (0..4).forEach {
                emit(it)
            }
        }.collect {
            hdyjLogd(it.toString())
        }

        flowOf(1, 2, 3, 4).collect { hdyjLogd(it.toString()) }

        listOf(1, 2).asFlow().collect { hdyjLogd(it.toString()) }

        (1..3).asFlow().transform { request -> emit("transform Int to String $request") }
            .collect { hdyjLogd(it) }
    }

    override fun initObserver() {
        mViewModel.mData.observe(this,
            { t ->
                val image = t.guanggao[0].imageurl
                mViewBinding.contentImage.load(image)
            })


    }

    override fun initListener() {

    }


}