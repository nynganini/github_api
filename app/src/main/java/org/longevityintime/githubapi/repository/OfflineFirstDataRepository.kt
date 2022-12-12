package org.longevityintime.githubapi.repository

import org.longevityintime.githubapi.database.GithubRepoDao
import org.longevityintime.githubapi.database.UserDao
import org.longevityintime.githubapi.database.model.GithubRepoEntity
import org.longevityintime.githubapi.database.model.UserEntity
import org.longevityintime.githubapi.database.model.asExternalModel
import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.model.User
import org.longevityintime.githubapi.model.asEntity
import org.longevityintime.githubapi.network.NetworkDataSource
import org.longevityintime.githubapi.network.NetworkResult
import javax.inject.Inject

class OfflineFirstDataRepository @Inject constructor(
    private val userDao: UserDao,
    private val githubRepoDao: GithubRepoDao,
    private val networkDataSource: NetworkDataSource
): DataRepository {

    override suspend fun getUsers(): List<User> {
        val dbUsers = userDao.getUsers()
        if(dbUsers.isNotEmpty()) return dbUsers.map(UserEntity::asExternalModel)
        val users = when(val networkUsers = networkDataSource.getUsers()){
            is NetworkResult.GenericError -> return listOf()
            NetworkResult.NetworkError -> return listOf()
            is NetworkResult.Success -> {
                userDao.upsertUsers(networkUsers.value.map(User::asEntity))
                networkUsers.value
            }
        }
        return users
    }

    override suspend fun getGithubRepo(login: String): List<GithubRepo> {
        val dbGithubRepos = githubRepoDao.getGithubRepos(login)
        if(dbGithubRepos.isNotEmpty()) return dbGithubRepos.map(GithubRepoEntity::asExternalModel)
        val repos = when(val networkRepos = networkDataSource.getRepositories(login)){
            is NetworkResult.GenericError -> listOf()
            NetworkResult.NetworkError -> listOf()
            is NetworkResult.Success -> {
                githubRepoDao.upsertGithubRepos(networkRepos.value.map {
                   it.run { GithubRepoEntity(id, name, htmlUrl, updatedAt, stargazersCount, language, login) }
                })
                networkRepos.value
            }
        }
        return repos
    }
}