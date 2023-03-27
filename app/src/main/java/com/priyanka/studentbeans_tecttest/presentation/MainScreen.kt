package com.priyanka.studentbeans_tecttest.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.priyanka.studentbeans_tecttest.R
import com.priyanka.studentbeans_tecttest.authorisation.presentation.login_screen.SignInScreen
import com.priyanka.studentbeans_tecttest.domain.model.DataModel
import com.priyanka.studentbeans_tecttest.ui.theme.StudentBeans_TectTestTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onNavToSignInPage: () -> Unit,) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text("Data") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable { onNavToSignInPage.invoke()
                            },
                        tint = Color.White
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.dataModel.size) { data ->
                val model = state.dataModel[data]
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    DataCard(
                        model = model,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { TODO() }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DataCard(
    model: DataModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 2.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = model.thumbnailUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Column {
            Text(
                text = model.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PrevHomeScreen() {
    StudentBeans_TectTestTheme{
        MainScreen(onNavToSignInPage = { /*TODO*/ })
    }
}




