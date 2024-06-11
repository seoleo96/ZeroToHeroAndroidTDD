package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rootLayout: LinearLayout
    private lateinit var titleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val removeButton = findViewById<Button>(R.id.removeButton)
        titleTextView = findViewById<TextView>(R.id.titleTextView)
        rootLayout = findViewById<LinearLayout>(R.id.rootLayout)
        removeButton.setOnClickListener {
            rootLayout.removeView(titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val isNeedRemove = rootLayout.childCount == 1
        outState.putBoolean(REMOVE_TEXT_VIEW_KEY, isNeedRemove)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val isNeedRemove = savedInstanceState.getBoolean(REMOVE_TEXT_VIEW_KEY)
        if (isNeedRemove)
            rootLayout.removeView(titleTextView)
    }

    companion object {
        private const val TAG = "MainActivity"
        const val REMOVE_TEXT_VIEW_KEY = "removeTextViewKey"
    }
}