package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface Repository {
    suspend fun load()
}

class RepositoryImpl : Repository {
    override suspend fun load() {
        withContext(Dispatchers.IO) {
            delay(3000)
        }
    }
}