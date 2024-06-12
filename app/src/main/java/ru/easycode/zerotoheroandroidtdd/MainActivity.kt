package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val count: Count = Count.Base(step = 2)
    private lateinit var countTextView: TextView

    private var countTextViewState: CountTextViewState = CountTextViewState.Initial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTextView = findViewById(R.id.countTextView)
        countTextViewState.apply(countTextView)

        findViewById<Button>(R.id.incrementButton).setOnClickListener {
            countTextViewState = CountTextViewState.Incremented(
                count.increment(countTextView.text.toString())
            )
            countTextViewState.apply(countTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(COUNT_TEXT_VIEW_STATE_KEY, countTextViewState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val state =
            savedInstanceState.getSerializable(COUNT_TEXT_VIEW_STATE_KEY) as CountTextViewState
        state.apply(countTextView)
    }

    companion object {
        private const val COUNT_TEXT_VIEW_STATE_KEY = "countTextViewState"
    }
}

interface CountTextViewState : Serializable {

    fun apply(textView: TextView)

    class Initial : CountTextViewState {
        override fun apply(textView: TextView) {
            textView.text = "0"
        }
    }

    class Incremented(private val count: String) : CountTextViewState {
        override fun apply(textView: TextView) {
            textView.text = count
        }
    }
}