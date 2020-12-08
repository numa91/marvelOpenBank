package com.numa91.marvelob.extensions

fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}
