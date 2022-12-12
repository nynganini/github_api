package org.longevityintime.githubapi.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.longevityintime.githubapi.viewmodel.UserViewModel

@Composable
fun UserScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel()
) {

}