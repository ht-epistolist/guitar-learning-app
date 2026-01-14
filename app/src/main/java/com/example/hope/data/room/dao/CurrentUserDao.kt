package com.example.hope.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.hope.data.room.entity.CurrentUser
import kotlinx.coroutines.flow.Flow

@Dao //creating a data access object
interface CurrentUserDao {
    @Upsert //adds new or updates the current user record
    suspend fun upsertCurrentUser(currentUser: CurrentUser)
    //suspend temporary stops the coroutine

    @Delete //deletes the current user record
    suspend fun deleteCurrentUser(currentUser: CurrentUser)

    @Query("SELECT*FROM currentUser LIMIT 1") //selects all the details of the current user
    fun getCurrentUser(): Flow<List<CurrentUser>>
    //flow is an observable, so also is able to notify about the changes in the database
}