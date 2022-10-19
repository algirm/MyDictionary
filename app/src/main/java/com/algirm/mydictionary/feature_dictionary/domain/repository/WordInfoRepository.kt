package com.algirm.mydictionary.feature_dictionary.domain.repository

import com.algirm.mydictionary.core.common.Resource
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

}