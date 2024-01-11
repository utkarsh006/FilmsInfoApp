package com.example.starwars.data.remote.dto

data class PersonBodyResponse(
    val next : String?,
    val previous : String?,
    val results : List<PersonDto>,
)
