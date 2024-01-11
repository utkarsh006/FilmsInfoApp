package com.example.starwars.data.remote

import com.example.starwars.domain.model.Film
import com.example.starwars.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilmRepoImpl @Inject constructor(
    private val personApi: PersonApi
) : FilmRepository {
    override suspend fun getFilmDetails(filmUrl: String): Flow<Film> = flow {
        emit(personApi.getFilm(filmUrl).toFilmDto())
    }.flowOn(Dispatchers.IO)
}