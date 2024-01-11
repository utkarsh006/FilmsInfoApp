package com.example.starwars.presentation.persons

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.starwars.R
import com.example.starwars.domain.model.Person
import com.example.starwars.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(
    navController: NavController,
    persons: LazyPagingItems<Person>,
    viewModel: PersonViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = persons.loadState) {
        if (persons.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (persons.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val isSheetOpen = remember { mutableStateOf(false) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            confirmValueChange = {
                isSheetOpen.value = it == SheetValue.Expanded
                true
            },
            skipPartiallyExpanded = false,
            initialValue = when (isSheetOpen.value) {
                false -> SheetValue.Hidden
                true -> SheetValue.Expanded
            }
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (persons.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            BottomSheetScaffold(
                content = {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(18.dp)
                    ) {

                        Button(
                            content = {
                                Text(stringResource(id = R.string.filters), color = Color.White)
                            },
                            onClick = {
                                //Log.d("pressed", "yes")
                                scope.launch {
                                    if (sheetState.bottomSheetState.currentValue != SheetValue.Expanded) {
                                        sheetState.bottomSheetState.expand()
                                        isSheetOpen.value = true
                                    } else {
                                        sheetState.bottomSheetState.hide()
                                        isSheetOpen.value = false
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(persons.itemSnapshotList.items) { person ->
                                PersonItem(
                                    person = person,
                                    modifier = Modifier.fillMaxWidth(),
                                    onItemClicked = {
                                        viewModel.onEvent(PersonEvent.OnPersonClicked(person))
                                        navController.navigate(Screen.PersonDetails.route)
                                    }
                                )
                            }

                            item {
                                if (persons.loadState.append is LoadState.Loading) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                    }
                },
                scaffoldState = sheetState,
                sheetContent = {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(stringResource(id = R.string.apply_filters))
                    }

                }
            )
        }
    }
}
