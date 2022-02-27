package com.example.ktorandroidapp.data.remote

import com.example.ktorandroidapp.data.remote.dto.MessageDto
import com.example.ktorandroidapp.domain.model.Message
import com.example.ktorandroidapp.util.Ressource
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.lang.reflect.Executable

class ChatSocketServiceImpl(
    private val client: HttpClient
) : ChatSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(username: String): Ressource<Unit> {
        return try {
            socket = client.webSocketSession {
                url(
                    "${ChatSocketService.Endpoints.chatSocket.url}?.username=$username"
                )
            }
            if (socket?.isActive == true) {
                return Ressource.Success(Unit)
            } else Ressource.Error("Couldn't etablish the connection")
        } catch (e: Exception) {
            e.printStackTrace()
            Ressource.Error(e.localizedMessage ?: "unknown error")
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming?.receiveAsFlow()?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDto = Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage()
                } ?: flow { }
        } catch (e: Exception) {
            e.printStackTrace()
            flow { }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}