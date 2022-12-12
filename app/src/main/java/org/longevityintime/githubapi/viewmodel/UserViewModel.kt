package org.longevityintime.githubapi.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.longevityintime.githubapi.network.NetworkDataSource
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val networkDataSource: NetworkDataSource
): ViewModel() {

}