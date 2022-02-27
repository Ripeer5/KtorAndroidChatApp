package com.example.ktorandroidapp.presentation.chat

import com.example.ktorandroidapp.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false

)
