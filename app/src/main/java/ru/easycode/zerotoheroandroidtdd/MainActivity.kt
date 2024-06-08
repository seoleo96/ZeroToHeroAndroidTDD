package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.changeButton).setOnClickListener {
            findViewById<TextView>(R.id.titleTextView).text = "I am an Android Developer!"
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedText = savedInstanceState.getString(TITLE_TEXT_VIEW_KEY)
        findViewById<TextView>(R.id.titleTextView).text = savedText

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            TITLE_TEXT_VIEW_KEY,
            findViewById<TextView>(R.id.titleTextView).text.toString()
        )
    }

    companion object {
        const val TITLE_TEXT_VIEW_KEY = "titleTextViewKey"
    }
}