package com.shopshop.util

import android.graphics.Color
import com.shopshop.model.*
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

data class User(
    val name: String,
    val email: String,
    val password: String,
    val token: String
)

class FakeDatabase {

    companion object {

        private var users: HashSet<User> = hashSetOf()
        private val items: LinkedHashSet<ItemResponse> = linkedSetOf()

        init {
            users.add(User("123", "123@gmail.com", "123", "abcd"))
            users.add(User("321", "321@gmail.com", "321", "dcba"))

            items.add(ItemResponse("Açai no pote", "Teste descrição açai no pote", Date().time, Color.BLUE))
            items.add(ItemResponse("1 duzia de laranja", "Teste descrição laranja", Date().time, Color.RED))
            items.add(ItemResponse("Arroz", "Teste descrição arroz", Date().time, Color.BLACK))
            items.add(ItemResponse("Feijao", "Teste descrição feijao", Date().time, Color.GREEN))

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


        fun getAll(token: String?, response: (GetAllResponse?) -> Unit ){
            Thread.sleep(2000)
            val list = mutableListOf<ItemResponse>()
            items.toCollection(list)
            response(GetAllResponse(list))
        }

    }
}