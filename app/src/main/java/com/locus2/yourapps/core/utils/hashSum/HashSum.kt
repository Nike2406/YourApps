package com.locus2.yourapps.core.utils.hashSum

import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import javax.inject.Inject

class HashSum @Inject constructor() {

    private val md: MessageDigest = MessageDigest.getInstance("SHA-1")

    fun calculate(file: File): String {
        return try {
            val inputStream = FileInputStream(file)
            val buffer = ByteArray(8192)
            var bytesRead = inputStream.read(buffer)

            while (bytesRead != -1) {
                md.update(buffer, 0, bytesRead)
                bytesRead = inputStream.read(buffer)
            }
            inputStream.close()

            val hashBytes = md.digest()
            val hexString = StringBuilder(hashBytes.size * 2)
            for (byte in hashBytes) {
                val hex = Integer.toHexString(0xff and byte.toInt())
                if (hex.length == 1) {
                    hexString.append('0')
                }
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: Exception) {
            Timber.e(e.message)
            throw RuntimeException("Error calculating hash sum")
        }
    }
}
