package com.example.roomapp.db

class WordRepository(private val wordDao: WordDao) {

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun getWords() = wordDao.getAlphabetizedWords()
}