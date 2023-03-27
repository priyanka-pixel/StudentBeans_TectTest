package com.priyanka.studentbeans_tecttest.presentation

import com.priyanka.studentbeans_tecttest.domain.model.DataModel

data class DataUiState(
    val dataModel: List<DataModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)