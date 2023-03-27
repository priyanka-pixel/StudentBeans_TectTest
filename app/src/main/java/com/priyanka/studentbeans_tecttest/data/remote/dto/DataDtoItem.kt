package com.priyanka.studentbeans_tecttest.data.remote.dto

import com.priyanka.studentbeans_tecttest.domain.model.DataModel

data class DataDtoItem(
    val albumId: Int = 0,
    val id: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val url: String = ""
)
fun DataDtoItem.toDataModel(): DataModel = DataModel(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)