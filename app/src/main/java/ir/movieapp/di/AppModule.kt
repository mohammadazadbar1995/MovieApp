package ir.movieapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.local.FavoriteDataBase
import ir.movieapp.data.repository.GenreRepository.GenreRepository
import ir.movieapp.data.repository.MoviesRepository
import ir.movieapp.util.preview.Constants.BASE_URL
import ir.movieapp.util.preview.Constants.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesTMDApi(okHttpClient: OkHttpClient): TMDBApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TMDBApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGenreRepository(api: TMDBApi): GenreRepository {
        return GenreRepository(api)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(api: TMDBApi): MoviesRepository {
        return MoviesRepository(api)
    }


    @Provides
    @Singleton
    fun providesFavoriteDatabase(application: Application): FavoriteDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoriteDataBase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}