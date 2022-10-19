package com.algirm.mydictionary.feature_dictionary.presentation.dictionary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.mydictionary.core.common.AppConst.Companion.SEARCH_QUERY_DELAY
import com.algirm.mydictionary.core.common.Resource
import com.algirm.mydictionary.feature_dictionary.domain.model.WordInfo
import com.algirm.mydictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val wordInfos = savedStateHandle.getStateFlow("wordInfos", emptyList<WordInfo>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isLoading = savedStateHandle.getStateFlow("isLoading", false)

    val state = combine(wordInfos, searchText, isLoading) { wordInfos, searchText, isLoading ->
        var isLoadingState = isLoading
        if (wordInfos.isNotEmpty()) {
            isLoadingState = false
        }
        WordInfoState(
            wordInfoItems = wordInfos,
            searchText = searchText,
            isLoading = isLoadingState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WordInfoState())

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        savedStateHandle["searchText"] = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_QUERY_DELAY)
            getWordInfo(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            savedStateHandle["wordInfos"] = result.data ?: emptyList()
                            savedStateHandle["isLoading"] = true
                        }
                        is Resource.Success -> {
                            savedStateHandle["wordInfos"] = result.data ?: emptyList()
                            savedStateHandle["isLoading"] = false
                        }
                        is Resource.Error -> {
                            savedStateHandle["wordInfos"] = result.data ?: emptyList()
                            savedStateHandle["isLoading"] = false
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                )
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }

}