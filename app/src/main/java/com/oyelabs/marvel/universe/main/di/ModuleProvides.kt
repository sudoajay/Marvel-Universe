package com.oyelabs.marvel.universe.main.di

import android.content.Context
import com.oyelabs.marvel.universe.main.proto.ProtoManager
import com.oyelabs.marvel.universe.main.ui.repository.PersonPagingAdapterGson
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
    fun providesPersonPagingAdapterGson( @ApplicationContext appContext: Context): PersonPagingAdapterGson = PersonPagingAdapterGson(appContext)


}

