package com.tizzone.getaround.data.di

import com.tizzone.getaround.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UrlProvideModule {
    @Provides
    fun provideBaseUrl() = BASE_URL
}
