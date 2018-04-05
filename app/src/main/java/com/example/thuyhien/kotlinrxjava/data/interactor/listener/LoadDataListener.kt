package com.example.thuyhien.kotlinrxjava.data.interactor.listener

/**
 * Created by thuyhien on 3/19/18.
 */
interface LoadDataListener<T> {
    fun onLoadDataSucces(data: T)

    fun onLoadDataFail(ex: Exception)
}