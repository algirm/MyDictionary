package com.algirm.mydictionary.feature_dictionary.data.remote

import com.algirm.mydictionary.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): Response<List<WordInfoDto>>
}