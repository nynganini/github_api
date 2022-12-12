package org.longevityintime.githubapi.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.longevityintime.githubapi.model.GithubRepo
import java.util.*

@Entity(
    tableName = "repositories",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("login"),
            childColumns = arrayOf("login")
        )
    ]
)
data class GithubRepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val htmlUrl: String,
    val updatedAt: Date,
    val stargazersCount: Long,
    val language: String?,
    val login: String // owner of the repository
)

fun GithubRepoEntity.asExternalModel(): GithubRepo = GithubRepo(id, name, htmlUrl, updatedAt, stargazersCount, language)