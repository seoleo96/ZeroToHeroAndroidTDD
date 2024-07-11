package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var uiState: UiState = Init
    private lateinit var textView: TextView
    private lateinit var actionButton: Button
    private lateinit var progressBar: ProgressBar

    private val viewModel = MainViewModel(LiveDataWrapperImpl(), RepositoryImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        actionButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(textView, progressBar, actionButton)
        }

        actionButton.setOnClickListener {
            viewModel.load()
        }
    }
}