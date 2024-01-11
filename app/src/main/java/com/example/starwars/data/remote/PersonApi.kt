package com.example.starwars.data.remote

import com.example.starwars.data.remote.dto.FilmDto
import com.example.starwars.data.remote.dto.PersonBodyResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PersonApi {

    @GET
    suspend fun getPersons(@Url url: String): PersonBodyResponse

    @GET
    suspend fun getFilm(@Url url: String): FilmDto

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }
}
