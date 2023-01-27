package com.example.a4_month_lesson_1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a4_month_lesson_1.model.Task

    @Database(entities = [Task::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun taskDao(): TaskDao
    }
