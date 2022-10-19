package com.algirm.mydictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.algirm.mydictionary.feature_dictionary.presentation.dictionary.DictionaryScreen
import com.algirm.mydictionary.ui.theme.MyDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDictionaryTheme {
//                val scaffoldState = rememberScaffoldState()
//                Scaffold(
//                    scaffoldState = scaffoldState
//                ) { _ ->
//                    Box(
//                        modifier = Modifier
//                            .background(MaterialTheme.colors.background)
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp)
//                        )
//                    }
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    DictionaryScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyDictionaryTheme {
        Greeting("Android")
    }
}