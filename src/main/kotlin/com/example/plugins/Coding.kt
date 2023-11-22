package com.example.plugins

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


fun String.encodeBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray())

fun String.encodeMD5(): String = MessageDigest.getInstance("MD5")
    .digest(this.toByteArray())
    .joinToString("") { "%02x".format(it) }

fun String.encodeSHA256(): String = MessageDigest.getInstance("SHA-256")
    .digest(this.toByteArray())
    .joinToString("") { "%02x".format(it) }