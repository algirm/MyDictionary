package com.algirm.mydictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.algirm.mydictionary.feature_dictionary.domain.model.Meaning
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>?,
    val phonetics: List<String>?,
    val word: String?,
    @PrimaryKey val id: Int? = null
) {

    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetics = phonetics,
            word = word
        )
    }

}