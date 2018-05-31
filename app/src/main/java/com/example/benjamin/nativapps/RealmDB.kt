package com.example.benjamin.nativapps

import android.app.Application
import io.realm.Realm


class RealmDB : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}