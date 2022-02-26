package com.example.ktorandroidapp.presentation.username

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.cio.websocket.*
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor(

): ViewModel() {
    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    
}