package com.numa91.marvelob.extensions

import java.security.MessageDigest

fun String.toMD5() =
    MessageDigest
        .getInstance("MD5")
        .digest(toByteArray())
        .toHex()

fun String.secureUrl() =
    this.replace("http","https")