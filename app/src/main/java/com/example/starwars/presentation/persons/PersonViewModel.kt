package com.example.starwars.presentation.persons

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.starwars.data.db.PersonEntity
import com.example.starwars.data.mappers.toPerson
import com.example.starwars.domain.model.Film
import com.example.starwars.domain.model.Person
import com.example.starwars.domain.useCases.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/* Role of pager in constructor is to provide a flow for the loaded entries and then to load the
next ones */

@HiltViewModel
class PersonViewModel @Inject constructor(
    pager: Pager<Int, PersonEntity>,
    private val filmUseCase: FilmUseCase
) : ViewModel() {

    var currentCharacter: MutableState<Person> = mutableStateOf(Person())
    var listOfFilms = mutableStateListOf<Film>()

    /* This flow will trigger a new emission of paging data every time we scroll and we want
    to trigger loading the next page */

    val personPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPerson() }
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnPersonClicked -> {
                currentCharacter.value = event.personDetails
                listOfFilms.clear()
                getAllFilms()
            }
        }
    }

    private fun getAllFilms() {
        viewModelScope.launch {
            currentCharacter.value.films.forEach { url ->
                filmUseCase.getSingleFilmDetails(url).collectLatest { film ->
                    Log.d("filmData", film.toString())
                    listOfFilms.add(film)
                }
            }
        }
    }
}

