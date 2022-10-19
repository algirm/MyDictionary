package com.algirm.mydictionary.feature_dictionary.data.remote.dto

data class PhoneticDto(
    val audio: String?,
    val licenseDto: LicenseDto?,
    val sourceUrl: String?,
    val text: String?
) {

    fun toPhonetic(): String? {
        return text
    }

}