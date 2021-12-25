package com.oyelabs.marvel.universe.main.di

import android.content.Context
import com.oyelabs.marvel.universe.main.proto.ProtoManager
import com.oyelabs.marvel.universe.main.ui.repository.CharacterPagingAdapterGson
import com.oyelabs.marvel.universe.scrolling.ui.repository.ComicPagingAdapterGson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvides {

    @Singleton
    @Provides
    fun providesProtoManger( @ApplicationContext appContext: Context): ProtoManager = ProtoManager(appContext)


    @Singleton
    @Provides
    fun providesCharacterPagingAdapterGson( @ApplicationContext appContext: Context): CharacterPagingAdapterGson = CharacterPagingAdapterGson(appContext)


    @Singleton
    @Provides
    fun providesComicPagingAdapterGson( @ApplicationContext appContext: Context): ComicPagingAdapterGson = ComicPagingAdapterGson(appContext)

}

