package com.shopshop.network

import com.shopshop.model.RequestUser

class RemoteDataSource() {

    fun login(requestUser: RequestUser, onUserLoggedIn: (String?, Throwable) -> Unit) {

    }

    fun register(requestUser: RequestUser, onUserRegiteredIn: (String?, Throwable?) -> Unit) {
        Thread {
            Thread.sleep(1000)
            onUserRegiteredIn("token", null)
        }.start()
    }
}