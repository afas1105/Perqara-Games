package com.example.perqaragames.base

import com.example.perqaragames.network.ApiClient
import com.example.perqaragames.network.ClientInterface

open class BasePresenter<T> {
    var view: T? = null

    var retrofitInterface: ClientInterface = ApiClient.getRetrofit().create(ClientInterface::class.java)

    val isViewNotNull: Boolean
        get() = view != null

    fun attachView(view: T) {
        this.view = view
    }

    fun dettachView() {
        this.view = null
    }
}