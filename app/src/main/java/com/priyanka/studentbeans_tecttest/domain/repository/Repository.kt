package com.priyanka.studentbeans_tecttest.domain.repository

import com.priyanka.studentbeans_tecttest.domain.model.DataModel
import com.priyanka.studentbeans_tecttest.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getDataList(): Flow<Resource<List<DataModel>>>
}