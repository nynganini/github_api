package org.longevityintime.githubapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.longevityintime.githubapi.model.GithubRepo
import org.longevityintime.githubapi.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val dataRepository: DataRepository,
): ViewModel() {

    var login: String = ""
        set(value) {
            field = value
            viewModelScope.launch {
                val githubRepos = dataRepository.getGithubRepo(value)
                _uiState.value = if(githubRepos.isNotEmpty()) GithubRepoUiState.Data(githubRepos) else GithubRepoUiState.Empty
            }
        }

    private var _uiState: MutableStateFlow<GithubRepoUiState> = MutableStateFlow(GithubRepoUiState.Loading)
    val uiState = _uiState.asStateFlow()


}

sealed interface GithubRepoUiState {
    object Loading: GithubRepoUiState
    data class Data(val githubRepoList: List<GithubRepo>): GithubRepoUiState
    object Empty: GithubRepoUiState
}