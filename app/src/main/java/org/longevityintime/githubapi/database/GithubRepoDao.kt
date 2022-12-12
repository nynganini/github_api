package org.longevityintime.githubapi.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.longevityintime.githubapi.database.model.GithubRepoEntity
import org.longevityintime.githubapi.database.model.UserEntity

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repositories WHERE login = :login")
    suspend fun getGithubRepos(login: String): List<GithubRepoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreGithubRepos(repos: List<GithubRepoEntity>): List<Long>

    @Update
    suspend fun updateGithubRepos(repos: List<GithubRepoEntity>)

    @Transaction
    suspend fun upsertGithubRepos(repos: List<GithubRepoEntity>) = upsert(
        items = repos, insertMany = ::insertOrIgnoreGithubRepos, updateMany = ::updateGithubRepos
    )
}