package com.jessz.cleanarchitectureexample.features.advice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jessz.cleanarchitectureexample.core.utils.ViewState
import com.jessz.cleanarchitectureexample.features.advice.di.AdviceModule
import com.jessz.cleanarchitectureexample.features.advice.domain.entities.AdviceEntity
import com.jessz.cleanarchitectureexample.ui.theme.CleanArchitectureExampleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: AdviceViewModel = AdviceModule.getViewModel()
                    FetchData(viewModel)
                }
            }
        }
    }
}


@Composable
fun FetchData(viewModel: AdviceViewModel) {
    LaunchedEffect(viewModel.advice) {
        viewModel.getAdvice()
    }

    val viewState by viewModel.advice.observeAsState()

    when (val result = viewState) {
        is ViewState.Success -> SuccessHandler(result.result.slip)
        is ViewState.Error -> ErrorHandler(result.message)
        is ViewState.Loading -> LoadingProgress()
        else -> return
    }
}

@Composable
fun ErrorHandler(message: String) {
    val snackBarHostState = remember { SnackbarHostState() }

    SnackbarHost(hostState = snackBarHostState)
    LaunchedEffect(key1 = message) {
        snackBarHostState.showSnackbar(message)
    }
}

@Composable
fun SuccessHandler(advice: AdviceEntity.Advice) {
    setupView(advice)
}

@Composable
fun setupView(adviceEntity: AdviceEntity.Advice) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Magenta,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = adviceEntity.advice,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }
    }

}

@Composable
fun LoadingProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp),
            color = Color.Green
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanArchitectureExampleTheme {}
}