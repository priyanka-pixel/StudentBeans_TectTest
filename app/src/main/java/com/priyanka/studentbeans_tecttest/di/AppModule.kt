package com.priyanka.studentbeans_tecttest.di

import com.priyanka.studentbeans_tecttest.data.remote.api.ApiService
import com.priyanka.studentbeans_tecttest.data.repository.RepoImpl
import com.priyanka.studentbeans_tecttest.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    })
    .build()

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiService.BASE_URL)
        .build()

    @Provides
    fun providesDataApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun providesRepository(
        apiService: ApiService
    ): Repository {
        return RepoImpl(apiService)
    }
}

