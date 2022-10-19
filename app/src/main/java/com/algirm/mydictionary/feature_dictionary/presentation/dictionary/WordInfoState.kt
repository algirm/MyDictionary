package com.algirm.mydictionary.feature_dictionary.presentation.dictionary

import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false
)