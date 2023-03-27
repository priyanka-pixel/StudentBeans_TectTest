package com.priyanka.studentbeans_tecttest.data.remote.api

import com.priyanka.studentbeans_tecttest.data.remote.dto.DataDto
import retrofit2.http.GET

interface ApiService {

    @GET(SHOW_END)
    suspend fun getList(
    ): DataDto

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val SHOW_END = "photos"
    }

}