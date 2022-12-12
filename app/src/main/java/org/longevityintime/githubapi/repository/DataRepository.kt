package org.longevityintime.githubapi.repository

import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.model.User

interface DataRepository {
    suspend fun getUsers(): List<User>
    suspend fun getGithubRepo(login: String): List<GithubRepo>
}