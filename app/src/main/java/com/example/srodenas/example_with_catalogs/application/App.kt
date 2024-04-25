package com.example.srodenas.example_with_catalogs.application

import android.app.Application
import androidx.room.Room
import com.example.srodenas.example_with_catalogs.data.users.database.UserDataBase
import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.domain.users.models.UserDataBaseSingleton

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        UserDataBaseSingleton.init(this)
    }
}