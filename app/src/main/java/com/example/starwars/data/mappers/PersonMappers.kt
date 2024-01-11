package com.example.starwars.data.mappers

import com.example.starwars.data.remote.dto.PersonDto
import com.example.starwars.data.db.PersonEntity
import com.example.starwars.domain.model.Person


fun PersonDto.toPersonEntity(next: String?): PersonEntity {
    return PersonEntity(
        url = url,
        name = name,
        gender = gender,
        birth_year = birth_year,
        films = films,
        next = next
    )
}

fun PersonEntity.toPerson(): Person {
    return Person(
        name = name,
        gender = gender,
        birth_year = birth_year,
        films = films
    )
}