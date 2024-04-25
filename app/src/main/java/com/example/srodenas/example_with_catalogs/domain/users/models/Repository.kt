package com.example.srodenas.example_with_catalogs.domain.users.models

import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.UserEntity


class Repository private constructor(private val userDao: UserDao) {
    companion object {
        val repo: Repository by lazy {
            Repository(UserDataBaseSingleton.getUserDao())
        }
    }

    suspend fun isLoginEntity(email: String, password: String): User? {
        val posUser: UserEntity? = userDao.login(email, password)
        return posUser?.let {
            User(it.id, it.name, it.email, it.password, it.phone, it.imag)
        }
    }

    suspend fun registerEntity(user: User): Boolean {
        val existingUser = isLoginEntity(user.email, user.passw)
        return if (existingUser == null) {
            try {
                val userEntity = UserEntity(user.id, user.name, user.email, user.passw, user.phone, user.imagen)
                userDao.insertUser(userEntity)
                true
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }
}