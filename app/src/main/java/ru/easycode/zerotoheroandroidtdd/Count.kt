package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun increment(number: String): UiState

    class Base(
        private val step: Int,
        private val max: Int,
    ) : Count {

        init {
            if (step < 1) {
                throw IllegalStateException("step should be positive, but was -2")
            }
            if (max < 1) {
                throw IllegalStateException("max should be positive, but was -2")
            }
            if (step > max) {
                throw IllegalStateException("max should be more than step")
            }
        }

        override fun increment(number: String): UiState {
            val numInt = number.toInt()
            val result = numInt + step
            val resultString = result.toString()
            if (result + step > max) {
                return UiState.Max(text = resultString)
            }
            return UiState.Base(text = resultString)
        }
    }
}