package com.example.starwars.domain.useCases

import com.example.starwars.domain.model.Film
import com.example.starwars.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmUseCase @Inject constructor(
    private val filmRepo: FilmRepository
) {
   suspend fun getSingleFilmDetails(filmUrl : String) : Flow<Film> {
        return filmRepo.getFilmDetails(filmUrl)
    }
}