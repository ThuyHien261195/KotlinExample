package com.example.thuyhien.kotlinrxjava.localDatabase.interactor.listener

/**
 * Created by thuyhien on 3/26/18.
 */
interface SQLiteListener<T> {
    fun loadDataSuccess(data: T)

    fun loadDataFail(error: Exception)
}