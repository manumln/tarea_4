package com.example.srodenas.example_with_catalogs.domain.users.models

import android.content.Context
import androidx.room.Room
import com.example.srodenas.example_with_catalogs.data.users.database.UserDataBase
import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao

object UserDataBaseSingleton {
    private var database: UserDataBase? = null
    private var userDao: UserDao? = null

    fun init(context: Context) {
        synchronized(this) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "my_app_user"
                ).build()
                userDao = database?.userDao()
            }
        }
    }

    fun getUserDao(): UserDao {
        return userDao ?: throw IllegalStateException("UserDao tiene que inicializarse antes de su uso")
    }
}
