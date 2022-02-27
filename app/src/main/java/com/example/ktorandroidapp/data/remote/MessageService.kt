package com.example.ktorandroidapp.data.remote

import com.example.ktorandroidapp.domain.model.Message

interface MessageService {

    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "http://192.168.104.24:8080"
    }

    sealed class Endpoints(val url: String) {
        object getAllMessages: Endpoints("$BASE_URL/message")
    }

}