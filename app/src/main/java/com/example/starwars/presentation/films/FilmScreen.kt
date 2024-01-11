package com.example.starwars.presentation.films

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwars.R
import com.example.starwars.presentation.persons.PersonViewModel

@Composable
fun FilmScreen(viewModel: PersonViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_person),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )

        Spacer(Modifier.height(8.dp))

        Text("Person Name: ${viewModel.currentCharacter.value.name}")
        Text("Person Gender: ${viewModel.currentCharacter.value.gender}")

        Spacer(Modifier.height(18.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            if (viewModel.listOfFilms.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(items = viewModel.listOfFilms) { film ->
                        FilmItem(film = film)
                    }
                }
            } else {
                Text(
                    text = stringResource(id = R.string.fetch_movies),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}