package com.algirm.mydictionary.feature_dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>?,
    val phonetics: List<String>?,
    val word: String?
) : java.io.Serializable
