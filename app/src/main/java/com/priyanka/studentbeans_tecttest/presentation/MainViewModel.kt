package com.priyanka.studentbeans_tecttest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanka.studentbeans_tecttest.domain.repository.Repository
import com.priyanka.studentbeans_tecttest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
private val repository: Repository

) : ViewModel() {
    private val _uiState = MutableStateFlow(DataUiState())
    val state: StateFlow<DataUiState> = _uiState.asStateFlow()

    init {
        getDataModel()
    }

    fun getDataModel() {
        //viewModelScope to launch a coroutine in the IO dispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDataList()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(dataModel = listings, isLoading = false) }
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(error = "error")
                        }
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }
}