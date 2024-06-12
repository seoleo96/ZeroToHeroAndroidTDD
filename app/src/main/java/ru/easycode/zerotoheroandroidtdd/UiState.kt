package ru.easycode.zerotoheroandroidtdd

sealed class UiState {

    data class Base(private val text: String) : UiState()
    data class Max(private val text: String) : UiState()
}