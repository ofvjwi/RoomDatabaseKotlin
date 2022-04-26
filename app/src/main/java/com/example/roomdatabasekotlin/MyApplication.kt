package com.example.roomdatabasekotlin

import android.app.Application
import android.util.Log

class MyApplication : Application() {

    companion object {
        private val TAG: String = MyApplication::class.java.simpleName.toString()
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate: ")
    }
}

