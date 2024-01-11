package com.example.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwars.presentation.films.FilmScreen
import com.example.starwars.presentation.persons.PersonScreen
import com.example.starwars.presentation.persons.PersonViewModel


@Composable
fun NavGraph() {
    val viewModel = hiltViewModel<PersonViewModel>()
    val persons = viewModel.personPagingFlow.collectAsLazyPagingItems()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.PersonList.route
    ) {
        composable(
            route = Screen.PersonList.route
        ) {
            PersonScreen(navController, persons, viewModel)
        }

        composable(
            route = Screen.PersonDetails.route
        ) {
            FilmScreen(viewModel)
        }
    }
}