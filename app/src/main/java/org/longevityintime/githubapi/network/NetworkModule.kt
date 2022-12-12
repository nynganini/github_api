package org.longevityintime.githubapi.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindsNetwork(networkDataSource: RetrofitNetwork): NetworkDataSource

    companion object {
        private const val BaseUrl = "https://api.github.com/"
        private val contentType = "application/json".toMediaType()

        @Provides
        @Singleton
        fun providesJson(): Json = Json { ignoreUnknownKeys = true }

        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        @Singleton
        fun providesNetworkApi(
            json: Json
        ): RetrofitNetworkApi =
            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(json.asConverterFactory(contentType))
                .build().create(RetrofitNetworkApi::class.java)
    }
}