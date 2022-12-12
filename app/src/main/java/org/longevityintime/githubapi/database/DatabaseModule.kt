package org.longevityintime.githubapi.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "github-database").build()

    @Provides
    fun providesUserDao(database: Database): UserDao = database.usersDao()
    @Provides
    fun providesGithubRepoDao(database: Database): GithubRepoDao = database.githubRepoDao()
}