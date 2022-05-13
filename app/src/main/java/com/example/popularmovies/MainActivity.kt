package com.example.popularmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.example.popularmovies.data.Movie
import com.example.popularmovies.data.MovieData
import com.example.popularmovies.ui.theme.PopularMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.isAppearanceLightNavigationBars = true
        setContent {
            PopularMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PopularMoviesMainPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesMainPage() {
    Column {
        TopAppBar(
            title = { Text(text = "Freddy's Popular Movies") },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.DarkGray
        )
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(MovieData.list) { item ->
                PopularMoviesListItem(item)
            }
        }
    }
}

@Composable
fun PopularMoviesListItem(movie: Movie) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(height = 300.dp)
    ) {
        Column {
            val painter = painterResource(movie.image)
            Image(
                painter = painter,
                contentDescription = "Sample movie image",
                modifier = Modifier
                    .height(height = 150.dp)
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth,
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(all = 8.dp),
                style = MaterialTheme.typography.h6
            )
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Air Date: ${movie.releaseDate}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Score: ${movie.voteAverage}/10",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Text(
                text = movie.overview,
                modifier = Modifier.padding(all = 8.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.caption
            )
        }
    }
}