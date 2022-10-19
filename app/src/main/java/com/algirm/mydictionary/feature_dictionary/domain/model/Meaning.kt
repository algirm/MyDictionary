package com.algirm.mydictionary.feature_dictionary.domain.model

data class Meaning(
    val antonyms: List<String>?,
    val definitions: List<Definition>?,
    val partOfSpeech: String?,
    val synonyms: List<String>?
) : java.io.Serializable
