package com.jessz.cleanarchitectureexample.features.advice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jessz.cleanarchitectureexample.R
import com.jessz.cleanarchitectureexample.core.utils.ViewState
import com.jessz.cleanarchitectureexample.features.advice.di.AdviceModule
import com.jessz.cleanarchitectureexample.features.advice.domain.entities.AdviceEntity
import com.jessz.cleanarchitectureexample.ui.theme.CleanArchitectureExampleTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AdviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = AdviceModule.getViewModel()

        setContent {
            CleanArchitectureExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    FetchData()
                }
            }
        }
    }

    @Composable
    fun FetchData() {
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
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Image(
                painter = painterResource(id = R.drawable.frame),
                contentDescription = stringResource(id = R.string.img_content_description),
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(end = 10.dp)
                    .align(Alignment.Center),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = adviceEntity.advice,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 20.dp)
                        .width(250.dp),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }
            FloatingActionButton(
                onClick = {
                    viewModel.getAdvice()
                },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape),

                ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    tint = Color.DarkGray,
                    contentDescription = getString(R.string.img_new_advice_content_description)
                )
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
                    .size(50.dp),
                color = Color.LightGray
            )
        }
    }

}