package com.example.starwars.domain.repository

import com.example.starwars.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getFilmDetails(filmUrl : String) : Flow<Film>
}