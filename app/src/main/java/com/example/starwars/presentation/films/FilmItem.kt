package com.example.starwars.presentation.films

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwars.domain.model.Film
import com.example.starwars.ui.theme.Purple80

@Composable
fun FilmItem(
    film: Film,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(Purple80),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Text(text = "Title : ${film.title}")
            Text(text = "Director Name : ${film.director}")
            Text("Release Date: ${film.release_date}")

        }
    }
}

@Preview
@Composable
fun FilmItemPreview() {
    FilmItem(
        film = Film(
            title = "3Idiots",
            director = "Raju Hirani",
            release_date = "2010"
        )
    )
}