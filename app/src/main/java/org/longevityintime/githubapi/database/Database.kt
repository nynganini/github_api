package org.longevityintime.githubapi.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import org.longevityintime.githubapi.database.model.GithubRepoEntity
import org.longevityintime.githubapi.database.model.UserEntity


@androidx.room.Database(
    version = 1,
    entities = [
        UserEntity::class,
        GithubRepoEntity::class
    ],
    autoMigrations = [

    ],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun usersDao(): UserDao
    abstract fun githubRepoDao(): GithubRepoDao
}