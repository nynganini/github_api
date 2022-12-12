package org.longevityintime.githubapi.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds fun bindsDataRepository(dataRepository: OfflineFirstDataRepository): DataRepository

}