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
            if(field != value) {
                field = value
                onUpdateUi()
            }
        }
    private var _uiState: MutableStateFlow<GithubRepoUiState> = MutableStateFlow(GithubRepoUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onUpdateUi(){
        viewModelScope.launch {
            _uiState.value = GithubRepoUiState.Loading
            val githubRepos = dataRepository.getGithubRepo(login)
            _uiState.value = if(githubRepos.isNotEmpty()) GithubRepoUiState.Data(githubRepos) else GithubRepoUiState.Empty
        }
    }
}

sealed interface GithubRepoUiState {
    object Loading: GithubRepoUiState
    data class Data(val githubRepoList: List<GithubRepo>): GithubRepoUiState
    object Empty: GithubRepoUiState
}