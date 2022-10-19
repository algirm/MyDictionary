package com.algirm.mydictionary.feature_dictionary.presentation.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier) {
        Text(
            text = wordInfo.word.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        var textPhonetics = ""
        wordInfo.phonetics?.forEach { phonetics ->
            textPhonetics = "$textPhonetics $phonetics"
        }
        Text(
            text = textPhonetics,
            fontWeight = FontWeight.Light
        )
//        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.meanings?.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech.toString(),
                fontWeight = FontWeight.Bold
            )
            meaning.definitions?.forEachIndexed { index, definition ->
                Text(
                    text = "${index + 1}. ${definition.definition}"
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (!definition.example.isNullOrBlank()) {
                    Text(text = "Example: ${definition.example}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}