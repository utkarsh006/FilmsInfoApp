package com.example.starwars.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.starwars.data.db.PersonDB
import com.example.starwars.data.db.PersonEntity
import com.example.starwars.data.remote.FilmRepoImpl
import com.example.starwars.data.remote.PersonApi
import com.example.starwars.data.remote.PersonRemoteMediator
import com.example.starwars.domain.repository.FilmRepository
import com.example.starwars.domain.useCases.FilmUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePersonDatabase(@ApplicationContext context: Context): PersonDB {
        return Room.databaseBuilder(
            context,
            PersonDB::class.java,
            "persons.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonApi(): PersonApi {
        return Retrofit.Builder()
            .baseUrl(PersonApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PersonApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFilmRepoImpl(personApi: PersonApi): FilmRepository {
        return FilmRepoImpl(personApi)
    }

    @Provides
    @Singleton
    fun providesFilmUseCase(filmRepo: FilmRepository): FilmUseCase {
        return FilmUseCase(filmRepo)
    }

    @Provides
    @Singleton
    fun providePersonPager(personDB: PersonDB, personApi: PersonApi): Pager<Int, PersonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PersonRemoteMediator(
                personDB = personDB,
                personApi = personApi
            ),
            pagingSourceFactory = {
                personDB.dao.pagingSource()
            }
        )
    }
}