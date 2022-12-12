package org.longevityintime.githubapi.database.model

import androidx.room.Entity

@Entity(tableName = "users")
class UserEntity(
    val id: Long,
    val login: String,
    val avatarUrl: String
)