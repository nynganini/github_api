package org.longevityintime.githubapi.database.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "repositories")
class GithubRepoEntity(
    val id: Long,
    val name: String,
    val htmlUrl: String,
    val updatedAt: Date,
    val stargazersCount: Long,
    val language: String
)