package org.longevityintime.githubapi.database

import androidx.room.*
import org.longevityintime.githubapi.database.model.UserEntity


@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreUsers(users: List<UserEntity>): List<Long>

    @Update
    suspend fun updateUsers(users: List<UserEntity>)

    @Transaction
    suspend fun upsertUsers(users: List<UserEntity>) = upsert(
        items = users, insertMany = ::insertOrIgnoreUsers, updateMany = ::updateUsers
    )
}