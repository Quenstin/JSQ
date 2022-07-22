package com.example.calculator.utils;

public interface CallbackBase<T> {

    void onSuccess(T entity);

    void onFailed(String errMsg);
}
