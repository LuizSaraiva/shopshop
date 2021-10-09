package com.shopshop.util

import com.shopshop.model.LoginResponse
import com.shopshop.model.RequestUser

data class Users(
    val name: String,
    val email: String,
    val password: String,
    val token: String
)

class FakeDatabase {

    companion object {

        private var users: HashSet<Users> = hashSetOf()

        init {
            users.add(Users("123", "123@gmail.com", "123", "abcd"))
            users.add(Users("321", "321@gmail.com", "321", "dcba"))
        }

        fun login(loginRequest: RequestUser, response: (LoginResponse?) -> Unit) {
            Thread.sleep(1000)
            val user: Users? = users.filter {
                it.email == loginRequest.email && it.password == loginRequest.password
            }.firstOrNull()

            if(user != null){
             response(LoginResponse(user.token))
            }else{
                response(null)
            }
        }
    }
}