package com.shopshop.network

import com.shopshop.model.RequestUser
import com.shopshop.util.FakeDatabase

class RemoteDataSource() {

    fun login(requestUser: RequestUser, onUserLoggedIn: (String?, Throwable?) -> Unit) {
        Thread {

            FakeDatabase.login(requestUser) { res ->
                if (res != null) {
                    onUserLoggedIn(res.token, null)
                } else {
                    onUserLoggedIn(null, null)
                }
            }
        }.start()
    }

    fun register(requestUser: RequestUser, onUserRegiteredIn: (String?, Throwable?) -> Unit) {
        Thread {
            Thread.sleep(1000)
            onUserRegiteredIn("token", null)
        }.start()
    }
}