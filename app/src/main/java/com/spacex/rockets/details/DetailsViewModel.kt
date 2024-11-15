package com.spacex.rockets.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacex.rockets.domain.rockets.Rocket
import com.spacex.rockets.domain.rockets.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rocketRepository: RocketRepository
): ViewModel() {

    sealed class State {
        data object Loading: State()
        data class Loaded(val rocket: Rocket): State()
        data class Error(val exception: Exception): State()
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    fun loadRocket(id: String) {
        state = State.Loading
        viewModelScope.launch {
            state = try {
                State.Loaded(rocketRepository.getRocket(id))
            } catch (ex: Exception) {
                ex.printStackTrace()
                State.Error(ex)
            }
        }
    }

}