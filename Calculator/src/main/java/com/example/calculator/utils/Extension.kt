package com.example.calculator.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

fun <T : View> getTypeChild(view:View,clazz: Class<T>): List<View> {
    if (view is ViewGroup) {
        val iterator = (view).children.toMutableList()
        val list = iterator.filter {
            it.javaClass==clazz
        }.toMutableList()

        for (v in iterator.filter {
            it is ViewGroup
        }) {
            list.addAll(getTypeChild(v, clazz))
        }
        return list
//        for (view in iterator) {
//            iterator.filter {
//                it.javaClass == clazz
//            }
//        }
    } else {
        return listOf()
    }
}