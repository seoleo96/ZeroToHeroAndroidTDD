package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(
        private val step: Int,
        private val max: Int,
        private val min: Int,
    ) : Count {

        init {
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was -2")
            }
            if (max < 0) {
                throw IllegalStateException("max should be positive, but was -2")
            }
            if (max < step) {
                throw IllegalStateException("max should be more than step")
            }
            if (max < min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            val numInt = number.toInt()
            val text = numInt.toString()
            return when (numInt) {
                min -> UiState.Min(text)
                max -> UiState.Max(text)
                else -> UiState.Base(text)
            }
        }

        override fun increment(number: String): UiState {
            val numInt = number.toInt()
            val result = numInt + step
            val text = result.toString()
            return when (result) {
                min -> UiState.Min(text)
                max -> UiState.Max(text)
                else -> UiState.Base(text)
            }
        }

        override fun decrement(number: String): UiState {
            val numInt = number.toInt()
            val result = numInt - step
            val text = result.toString()
            return when (result) {
                min -> UiState.Min(text)
                max -> UiState.Max(text)
                else -> UiState.Base(text)
            }
        }
    }
}