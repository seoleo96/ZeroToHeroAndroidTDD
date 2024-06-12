package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var rootLayout: LinearLayout
    private lateinit var removeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)
        removeButton.setOnClickListener {
            rootLayout.removeView(titleTextView)
            removeButton.isEnabled = false

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val needRemoveTitleTextView = rootLayout.childCount == 1
        outState.putBoolean(REMOVE_TITLE_TEXT_VIEW_KEY, needRemoveTitleTextView)
        outState.putBoolean(NOT_ENABLED_REMOVE_BUTTON_KEY, removeButton.isEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getBoolean(REMOVE_TITLE_TEXT_VIEW_KEY).apply {
            if (this) {
                rootLayout.removeView(titleTextView)
            }
        }
        savedInstanceState.getBoolean(NOT_ENABLED_REMOVE_BUTTON_KEY).apply {
            removeButton.isEnabled = this
        }
    }

    companion object {
        private const val REMOVE_TITLE_TEXT_VIEW_KEY = "removeTextView"
        private const val NOT_ENABLED_REMOVE_BUTTON_KEY = "notEnabledRemovedButton"
    }
}