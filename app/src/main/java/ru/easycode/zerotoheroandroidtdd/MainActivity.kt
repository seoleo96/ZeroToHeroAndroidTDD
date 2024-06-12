package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var rootLayout: LinearLayout
    private lateinit var removeButton: Button

    private var titleTextViewState: TitleTextViewState = Initial
    private var removeButtonState: RemoveButtonState = InitialRemoveButtonState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)
        removeButton.setOnClickListener {
            titleTextViewState = Removed
            removeButtonState = Enabled
            titleTextViewState.apply(rootLayout, titleTextView)
            removeButtonState.apply(removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(REMOVE_TITLE_TEXT_VIEW_KEY, titleTextViewState)
        outState.putSerializable(NOT_ENABLED_REMOVE_BUTTON_KEY, removeButtonState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        titleTextViewState =
            savedInstanceState.getSerializable(REMOVE_TITLE_TEXT_VIEW_KEY) as TitleTextViewState
        removeButtonState =
            savedInstanceState.getSerializable(NOT_ENABLED_REMOVE_BUTTON_KEY) as RemoveButtonState
        titleTextViewState.apply(rootLayout, titleTextView)
        removeButtonState.apply(removeButton)
    }

    companion object {
        private const val REMOVE_TITLE_TEXT_VIEW_KEY = "removeTextView"
        private const val NOT_ENABLED_REMOVE_BUTTON_KEY = "notEnabledRemovedButton"
    }
}

interface TitleTextViewState : Serializable {
    fun apply(linearLayout: LinearLayout, textView: TextView)
}

interface RemoveButtonState : Serializable {
    fun apply(button: Button)
}

object InitialRemoveButtonState : RemoveButtonState {
    private fun readResolve(): Any = Initial
    override fun apply(button: Button) = Unit
}

object Enabled : RemoveButtonState {
    private fun readResolve(): Any = Removed
    override fun apply(button: Button) {
        button.isEnabled = false
    }
}

object Initial : TitleTextViewState {
    private fun readResolve(): Any = Initial
    override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
}

object Removed : TitleTextViewState {
    private fun readResolve(): Any = Removed
    override fun apply(linearLayout: LinearLayout, textView: TextView) {
        linearLayout.removeView(textView)
    }
}