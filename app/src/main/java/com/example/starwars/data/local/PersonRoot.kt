package com.example.starwars.data.local

data class PersonRoot(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Person>
)