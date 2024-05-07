package com.eshopthis.finds.di

import android.content.Context
import com.eshopthis.finds.API.ApiService
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.data.ItemDAO
import com.eshopthis.finds.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://mnsso.wamfeo.com/sso.mn.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase): ItemDAO {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: AppDatabase, apiService: ApiService): UserRepository {
        return UserRepository(appDatabase, apiService)
    }
}