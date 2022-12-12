package org.longevityintime.githubapi.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.longevityintime.githubapi.viewmodel.GithubRepoViewModel

object Routes {
    const val USERS = "users"
    const val GITHUB_REPOS = "repos/{login}"
}

@Composable
fun GithubNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.USERS,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        userGraph { destination ->
            navController.navigate(destination)
        }
        repositoryGraph {
            navController.popBackStack()
        }
    }
}

fun NavGraphBuilder.userGraph(onNavigate: (String) -> Unit) {
    composable(route = Routes.USERS){
        UserScreen(onNavigate = onNavigate)
    }
}
fun NavGraphBuilder.repositoryGraph(onBack: () -> Unit) {
    composable(
        route = Routes.GITHUB_REPOS,
        arguments = listOf(
            navArgument("login") { type = NavType.StringType }
        )
    ){
        val login = it.arguments?.getString("login")!!
        val viewModel: GithubRepoViewModel = hiltViewModel()
        viewModel.login = login
        GithubRepoScreen(viewModel = viewModel, onBack = onBack)
    }
}