package com.bharath.simplemusicplayer.di

import com.bharath.simplemusicplayer.data.local.AudioGetterC
import com.bharath.simplemusicplayer.data.repository.RepoImpl
import com.bharath.simplemusicplayer.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRepository(audioGetterC: AudioGetterC):Repository{
        return RepoImpl(audioGetterC)
    }


}