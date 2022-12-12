@file:OptIn(ExperimentalLifecycleComposeApi::class)

package org.longevityintime.githubapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.longevityintime.githubapi.R
import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.viewmodel.GithubRepoUiState
import org.longevityintime.githubapi.viewmodel.GithubRepoViewModel

@Composable
fun GithubRepoScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(uiState){
        is GithubRepoUiState.Data -> {
            val githubRepos = (uiState as GithubRepoUiState.Data).githubRepoList
            GithubRepoListScreen(viewModel.login, githubRepos = githubRepos, onBack = onBack)
        }
        GithubRepoUiState.Empty -> {}
        GithubRepoUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.github_repo_loading), Modifier.padding(bottom = 10.dp))
                    LinearProgressIndicator()
                }
            }
        }
    }
}
@Composable
fun GithubRepoListScreen(
    login: String,
    githubRepos: List<GithubRepo>,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val tint = MaterialTheme.colorScheme.onBackground
    Scaffold(
        modifier = modifier.statusBarsPadding(),
        topBar = {
            Row{
                IconButton(
                    onClick = onBack,
                    Modifier
                        .padding(start = 10.dp, end = 20.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = tint
                    )
                }
                Text(
                    text = login,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .navigationBarsPadding()
        ) {
            githubRepos.forEach { githubRepo ->
                item {
                    GithubRepoScreen(githubRepo = githubRepo)
                }
            }
        }
    }
}

@Composable
fun GithubRepoScreen(
    githubRepo: GithubRepo,
    modifier: Modifier = Modifier
) {

}