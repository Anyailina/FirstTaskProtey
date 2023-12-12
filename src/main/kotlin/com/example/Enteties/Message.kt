package com.example.Enteties

import java.util.*

data class Message(
    val messageId: UUID,
    val chatId: UUID,
    val userId: UUID,
    val recipientId: UUID,
    val messageContent: String,
    val timestamp: String?
)
