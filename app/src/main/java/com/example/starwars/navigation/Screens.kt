package com.example.starwars.navigation

sealed class Screen(val route: String) {
    object PersonList : Screen("PersonScreen")
    object PersonDetails : Screen("FilmScreen")
}