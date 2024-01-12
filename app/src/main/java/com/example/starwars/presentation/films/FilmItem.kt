package com.example.starwars.presentation.films

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Title : ${film.title}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Director Name : ${film.director}")

            Spacer(modifier = Modifier.height(8.dp))

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