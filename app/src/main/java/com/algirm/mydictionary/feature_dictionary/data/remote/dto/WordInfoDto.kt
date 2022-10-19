package com.algirm.mydictionary.feature_dictionary.data.remote.dto

import com.algirm.mydictionary.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val licenses: LicenseDto?,
    val meanings: List<MeaningDto>?,
    val phonetics: List<PhoneticDto>?,
    val sourceUrls: List<String>?,
    val word: String?
) {

    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings?.map { it.toMeaning() },
            phonetics = phonetics?.mapNotNull { it.toPhonetic() },
            word = word
        )
    }

}