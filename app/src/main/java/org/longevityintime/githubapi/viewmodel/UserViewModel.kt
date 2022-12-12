package org.longevityintime.githubapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.longevityintime.githubapi.model.User
import org.longevityintime.githubapi.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private var _uiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val users = dataRepository.getUsers()
            _uiState.value = if(users.isNotEmpty()) UserUiState.Data(users) else UserUiState.Empty
        }
    }
}

sealed interface UserUiState {
    object Loading: UserUiState
    data class Data(val userList: List<User>): UserUiState
    object Empty: UserUiState
}