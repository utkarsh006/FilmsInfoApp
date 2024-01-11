package com.example.starwars.domain.model

data class Person(
    val name: String = "",
    val gender: String ="",
    val birth_year: String = "",
    val films: List<String> = listOf()
)
