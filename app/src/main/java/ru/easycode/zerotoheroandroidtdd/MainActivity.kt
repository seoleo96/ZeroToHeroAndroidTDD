package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.hideButton).setOnClickListener {
            findViewById<TextView>(R.id.titleTextView).isVisible = false
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        findViewById<TextView>(R.id.titleTextView).isVisible = savedInstanceState.getBoolean(
            TITLE_VISIBLE_KEY
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(TITLE_VISIBLE_KEY, findViewById<TextView>(R.id.titleTextView).isVisible)
    }

    companion object {
        const val TITLE_VISIBLE_KEY = "titleVisibleKey"
    }
}