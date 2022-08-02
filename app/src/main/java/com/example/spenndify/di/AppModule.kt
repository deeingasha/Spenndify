package com.example.spenndify.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spenndify.data.local.AppDatabase
import com.example.spenndify.data.remote.ApiService
import com.example.spenndify.model.User
import com.example.spenndify.repo.userRepository.UserRepository
import com.example.spenndify.repo.userRepository.UserRepositoryImpl
import com.example.spenndify.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://spenndify-expenses-tracker-app.herokuapp.com/"

    private fun getOkHttpClient(context: Context):OkHttpClient.Builder =
        try {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor{
                AuthInterceptor(context).intercept(it)
            }
            builder
        }catch (e:Exception){
            throw RuntimeException(e)
        }

    @Provides
    @Singleton
    fun provideToDoDatabase(app: Application):AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: AppDatabase): UserRepository {

        return UserRepositoryImpl(database.getUserDao())
    }

    @Provides
    @Singleton
    fun provideRetrofitCall(@ApplicationContext context: Context): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient(context).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(retrofit: Retrofit): ApiService {

        return retrofit.create(ApiService::class.java)
    }

}
