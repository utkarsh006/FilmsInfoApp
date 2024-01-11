package com.example.starwars.presentation.persons

import com.example.starwars.domain.model.Person

sealed class PersonEvent {
    data class OnPersonClicked(val personDetails: Person) : PersonEvent()
}