package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var countTextView: TextView
    private lateinit var uiState: UiState

    private val count: Count = Count.Base(step = 2, max = 4, min = 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        countTextView = findViewById(R.id.countTextView)

        if (savedInstanceState == null) {
            uiState = count.initial("0")
            updateUiState()
        }

        incrementButton.setOnClickListener {
            uiState = count.increment(countTextView.text.toString())
            updateUiState()
        }

        decrementButton.setOnClickListener {
            uiState = count.decrement(countTextView.text.toString())
            updateUiState()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable(KEY) as UiState
        updateUiState()
    }

    private fun updateUiState() {
        uiState.apply(incrementButton, decrementButton, countTextView)
    }

    companion object {
        private const val KEY = "key"
    }
}