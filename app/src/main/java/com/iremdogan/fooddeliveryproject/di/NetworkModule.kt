package com.iremdogan.fooddeliveryproject.di

import com.google.gson.Gson
import com.iremdogan.fooddeliveryproject.BuildConfig
import com.iremdogan.fooddeliveryproject.model.local.LocalDataSource
import com.iremdogan.fooddeliveryproject.model.remote.APIService
import com.iremdogan.fooddeliveryproject.model.remote.RemoteDataSource
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
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(localDataSource: LocalDataSource): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if(localDataSource.getToken().isNullOrEmpty()){
            builder.interceptors().add(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
        } else {
            builder.addInterceptor {
                val request = it.request().newBuilder().addHeader("Authorizaton", "Bearer " + localDataSource.getToken()!!).build()
                it.proceed(request)
            }
        }
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        // TODO : endpoint will be changed
        return EndPoint("https://dist-learn.herokuapp.com/api/")
    }

}

data class EndPoint(val url: String)