package com.example.ktorandroidapp.data.remote.dto

import com.example.ktorandroidapp.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto(
    val text: String,
    val timestanp: Long,
    val username: String,
    val id: String
){
    fun toMessage(): Message {
        val date = Date(timestanp)
        val formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)
        return Message(
            text,
            formattedDate,
            username
        )
    }
}