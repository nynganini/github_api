package org.longevityintime.githubapi.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.longevityintime.githubapi.model.User

@Entity(
    tableName = "users",
    indices = [Index(value = ["login"], unique = true)]
)
data class UserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val avatarUrl: String
)

fun UserEntity.asExternalModel(): User = User(id, login, avatarUrl)