package com.shopshop.util

import com.shopshop.model.LoginResponse
import com.shopshop.model.RegisterRequest
import com.shopshop.model.RegisterResponse
import com.shopshop.model.RequestUser
import java.util.*
import kotlin.collections.HashSet

data class User(
    val name: String,
    val email: String,
    val password: String,
    val token: String
)

class FakeDatabase {

    companion object {

        private var users: HashSet<User> = hashSetOf()

        init {
            users.add(User("123", "123@gmail.com", "123", "abcd"))
            users.add(User("321", "321@gmail.com", "321", "dcba"))
        }

        fun login(loginRequest: RequestUser, response: (LoginResponse?) -> Unit) {
            Thread.sleep(1000)
            val user: User? = users.filter {
                it.email == loginRequest.email && it.password == loginRequest.password
            }.firstOrNull()

            if (user != null) {
                response(LoginResponse(user.token))
            } else {
                response(null)
            }
        }

        fun register(request: RegisterRequest, response: (RegisterResponse?) -> Unit) {

            Thread.sleep(2000)

            val newUser = User(
                name = request.name,
                email = request.email,
                password = request.password,
                token = UUID.randomUUID().toString()
            )

            val exists = users.filter {
                it.email == newUser.email
            }.firstOrNull()

            if (exists != null) {
                response(null)
            } else {
                users.add(newUser)
                response(RegisterResponse(newUser.token))
            }
        }
    }
}