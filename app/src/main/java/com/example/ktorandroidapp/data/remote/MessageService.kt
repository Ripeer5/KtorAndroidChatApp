package com.example.ktorandroidapp.data.remote

import com.example.ktorandroidapp.domain.model.Message

interface MessageService {

    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://10.47.0.214:8080"
    }

    sealed class Endpoints(val url: String) {
        object getAllMessages: Endpoints("$BASE_URL/messages")
    }

}