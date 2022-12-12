package org.longevityintime.githubapi.network

import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.model.User

interface NetworkDataSource {
    suspend fun getUsers(): NetworkResult<List<User>>
    suspend fun getRepositories(login: String): NetworkResult<List<GithubRepo>>
}

sealed interface NetworkResult<out T> {
    data class Success<out T>(val value: T): NetworkResult<T>
    data class GenericError(val code: Int? = null, val error: String? = null): NetworkResult<Nothing>
    object NetworkError: NetworkResult<Nothing>
}