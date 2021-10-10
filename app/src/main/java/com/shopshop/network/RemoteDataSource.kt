package com.shopshop.network

import com.shopshop.App
import com.shopshop.model.ItemResponse
import com.shopshop.model.RegisterRequest
import com.shopshop.model.RequestUser
import com.shopshop.util.FakeDatabase

class RemoteDataSource() {

    fun login(requestUser: RequestUser, onUserLoggedIn: (String?, Throwable?) -> Unit) {
        Thread {

            FakeDatabase.login(requestUser) { res ->
                if (res != null) {
                    App.saveToken(res.token)
                    onUserLoggedIn(res.token, null)
                } else {
                    onUserLoggedIn(null, null)
                }
            }
        }.start()
    }

    fun register(registerRequest: RegisterRequest, onUserCreated: (String?, Throwable?) -> Unit) {
        Thread {

            FakeDatabase.register(registerRequest){res ->
                if(res != null){
                    App.saveToken(res.token)
                    onUserCreated(res.token, null)
                }else{
                    onUserCreated(null, null)
                }
            }
        }.start()
    }

    fun getAll(onResponse:(List<ItemResponse>?, Throwable?) -> Unit){
        Thread{
            FakeDatabase.getAll(App.getToken()){ res ->
                if(res != null){
                    onResponse(res.list, null)
                }else{
                    onResponse(null, null)
                }
            }
        }.start()
    }
}