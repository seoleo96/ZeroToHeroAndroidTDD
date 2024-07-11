package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {
    fun apply(textView: TextView, progressBar: ProgressBar, button: Button)
}

object Init : UiState {
    override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
        progressBar.isVisible = false
        textView.isVisible = false
        button.isEnabled = true
    }
}

object Loading : UiState {
    override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
        progressBar.isVisible = true
        textView.isVisible = false
        button.isEnabled = false
    }
}

object Loaded : UiState {
    override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
        progressBar.isVisible = false
        textView.isVisible = true
        button.isEnabled = true
    }
}