package com.priyanka.studentbeans_tecttest.data.repository

import com.priyanka.studentbeans_tecttest.data.remote.api.ApiService
import com.priyanka.studentbeans_tecttest.data.remote.dto.toDataModel
import com.priyanka.studentbeans_tecttest.domain.model.DataModel
import com.priyanka.studentbeans_tecttest.domain.repository.Repository
import com.priyanka.studentbeans_tecttest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val apiService: ApiService,

    ) : Repository {
    override suspend fun getDataList(): Flow<Resource<List<DataModel>>> {
        return flow {
            emit(Resource.Loading())
            val dataModel = try {
                apiService.getList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            }
            dataModel?.let { listings ->
                emit(Resource.Success(
                    data = listings.map { it.toDataModel() }
                ))
            }
        }
    }

}
