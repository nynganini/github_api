@file:OptIn(ExperimentalLifecycleComposeApi::class)

package org.longevityintime.githubapi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.longevityintime.githubapi.R
import org.longevityintime.githubapi.model.User
import org.longevityintime.githubapi.viewmodel.UserUiState
import org.longevityintime.githubapi.viewmodel.UserViewModel

@Composable
fun UserScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(uiState){
        is UserUiState.Data -> {
            UserListScreen(
                userList = (uiState as UserUiState.Data).userList,
                onUserSelected = { login ->
                    val githubRepoRoute = Routes.GITHUB_REPOS.substring(0, Routes.GITHUB_REPOS.indexOf('/'))
                    val githubRepoPath = "$githubRepoRoute/$login"
                    onNavigate(githubRepoPath)
                },
                modifier = modifier
            )
        }
        UserUiState.Empty -> {}
        UserUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.user_loading), Modifier.padding(bottom = 10.dp))
                    LinearProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun UserListScreen(
    userList: List<User>,
    onUserSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier){
        item {
            UserAppBar()
        }
        userList.forEach { user ->
            item {
                UserEltScreen(user = user, onUserSelected = onUserSelected)
            }
        }
    }
}
@Composable
fun UserEltScreen(
    user: User,
    onUserSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colorScheme.surface,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .clickable { onUserSelected(user.login) }
        ) {
            NetworkImage(
                url = user.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f)
            )
            Text(
                text = user.login,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun UserAppBar(

) {
    TopAppBar(elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(80.dp)) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}