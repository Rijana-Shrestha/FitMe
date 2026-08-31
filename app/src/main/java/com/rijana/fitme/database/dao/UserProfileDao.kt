package com.rijana.fitme.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rijana.fitme.database.entity.UserProfile

@Dao
interface UserProfileDao {

    @Insert
    suspend fun insertUserProfile(profile: UserProfile)

    @Query("SELECT * FROM user_profile WHERE userId = :userId")
    suspend fun getUserProfileByUserId(userId: Int): UserProfile?

    @Update
    suspend fun updateUserProfile(profile: UserProfile)

    @Delete
    suspend fun deleteUserProfile(profile: UserProfile)
}