package com.algirm.mydictionary.feature_dictionary.domain.use_case

import com.algirm.mydictionary.core.common.Resource
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo
import com.algirm.mydictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetWordInfo @Inject constructor(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return emptyFlow()
        }
        return repository.getWordInfo(word)
    }
}