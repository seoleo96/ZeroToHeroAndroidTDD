package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(incrementButton: Button, decrementButton: Button, countTextView: TextView)

    data class Base(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView
        ) {
            decrementButton.isEnabled = true
            incrementButton.isEnabled = true
            countTextView.text = text
        }
    }

    data class Max(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView
        ) {
            decrementButton.isEnabled = true
            incrementButton.isEnabled = false
            countTextView.text = text
        }
    }

    data class Min(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView
        ) {
            decrementButton.isEnabled = false
            incrementButton.isEnabled = true
            countTextView.text = text
        }
    }

}
