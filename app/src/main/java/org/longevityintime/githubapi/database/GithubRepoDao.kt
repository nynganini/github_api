package org.longevityintime.githubapi.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.longevityintime.githubapi.database.model.GithubRepoEntity

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repositories")
    fun getGithubRepos(): Flow<List<GithubRepoEntity>>
}