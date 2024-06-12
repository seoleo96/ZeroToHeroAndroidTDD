package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var rootLayout: LinearLayout
    private lateinit var titleTextView: TextView
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val removeButton = findViewById<Button>(R.id.removeButton)
        titleTextView = findViewById<TextView>(R.id.titleTextView)
        rootLayout = findViewById<LinearLayout>(R.id.rootLayout)
        removeButton.setOnClickListener {
            state = State.Removed
            state.apply(linearLayout = rootLayout, textView = titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(REMOVE_TEXT_VIEW_KEY, state)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable(REMOVE_TEXT_VIEW_KEY, State::class.java) as State
        state.apply(linearLayout = rootLayout, textView = titleTextView)
    }

    companion object {
        private const val TAG = "MainActivity"
        const val REMOVE_TEXT_VIEW_KEY = "removeTextViewKey"
    }
}

interface State : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView)

    object Initial : State {
        private fun readResolve(): Any = Initial
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : State {
        private fun readResolve(): Any = Removed
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}