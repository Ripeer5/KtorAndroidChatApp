package com.example.ktorandroidapp.data.remote

import com.example.ktorandroidapp.domain.model.Message
import com.example.ktorandroidapp.util.Ressource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Ressource<Unit>

    suspend fun sendMessage(
        message: String
    )

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://10.47.0.214:8080"
    }

    sealed class Endpoints(val url: String) {
        object chatSocket: Endpoints("$BASE_URL/chat-socket")
    }

}