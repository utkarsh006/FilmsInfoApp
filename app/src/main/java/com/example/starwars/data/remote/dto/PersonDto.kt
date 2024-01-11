package com.example.starwars.data.remote.dto

data class PersonDto(
    val name: String,
    val gender: String,
    val birth_year: String,
    val url: String,
    val films: List<String>
)
