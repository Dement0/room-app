package com.example.roomapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.db.Word
import com.example.roomapp.db.WordRepository
import com.example.roomapp.db.WordRoomDatabase
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: MutableLiveData<List<Word>> = MutableLiveData()

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        updateWords()
    }

    private fun updateWords() = viewModelScope.launch {
        allWords.postValue(repository.getWords())
    }

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
        updateWords()
    }
}