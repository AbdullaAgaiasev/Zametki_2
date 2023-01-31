package com.example.a4_month_lesson_1.data.db

import androidx.room.*
import com.example.a4_month_lesson_1.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}