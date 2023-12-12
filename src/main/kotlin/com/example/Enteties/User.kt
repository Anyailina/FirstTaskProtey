package com.example.Enteties

import java.util.*

data class User(
    val userId: UUID,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?
)
