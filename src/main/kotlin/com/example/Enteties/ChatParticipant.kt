package com.example.Enteties

import java.util.*

data class ChatParticipant(
    val chatId: UUID,
    val userId: UUID,
    val joinDate: String?,
)
