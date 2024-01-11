package com.example.starwars.presentation.persons

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.example.starwars.domain.model.Person
import com.example.starwars.ui.theme.Pink80

@Composable
fun PersonItem(
    person: Person,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onItemClicked() },
        colors = CardDefaults.cardColors(Pink80),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Text(text = "Name : ${person.name}")

            Text(text = "Gender : ${person.gender}")

            Text(text = "BirthYear : ${person.birth_year}")

        }
    }
}

