package org.longevityintime.githubapi.network

import android.util.Log
import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.model.User
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException
import javax.inject.Inject

interface RetrofitNetworkApi {
    @GET("/users")
    suspend fun getUsers(): List<User>
    @GET("/users/{login}/repos")
    suspend fun getRepos(@Path("login") login: String): List<GithubRepo>
}

class RetrofitNetwork @Inject constructor(
    private val networkApi: RetrofitNetworkApi
): NetworkDataSource {
    override suspend fun getUsers(): NetworkResult<List<User>> =
        safeApiCall {
            networkApi.getUsers()
        }

    override suspend fun getRepositories(login: String): NetworkResult<List<GithubRepo>> =
        safeApiCall {
            networkApi.getRepos(login)
        }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(apiCall.invoke())
    } catch (throwable: Throwable){
        Log.i("longevity-retrofit", "error: $throwable")
        when(throwable){
            is IOException -> NetworkResult.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = throwable.response()?.errorBody()?.source()
                NetworkResult.GenericError(code, errorResponse?.readUtf8())
            }
            else -> NetworkResult.GenericError(null, null)
        }
    }
}