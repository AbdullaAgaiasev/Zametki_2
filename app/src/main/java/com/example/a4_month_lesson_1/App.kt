package com.example.a4_month_lesson_1

import android.app.Application
import androidx.room.Room
import com.example.a4_month_lesson_1.data.db.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

    }

    companion object{
        lateinit var db: AppDatabase
    }
}