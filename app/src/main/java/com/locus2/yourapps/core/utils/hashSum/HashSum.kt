package com.locus2.yourapps.core.utils.hashSum

import android.content.pm.Signature
import android.util.Base64
import com.locus2.yourapps.core.utils.constant.EMPTY_STRING
import timber.log.Timber
import java.security.MessageDigest
import javax.inject.Inject

class HashSum @Inject constructor() {

    private val md: MessageDigest = MessageDigest.getInstance("SHA-1")

    fun calculate(signatures: Array<Signature>): String {
        return try {
            val hashes = mutableListOf<String>()

            for (s in signatures) {
                md.update(s.toByteArray())
                val en = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                hashes.add(en)
            }
            hashes.joinToString(EMPTY_STRING)
        } catch (e: Exception) {
            Timber.e(e.message)
            throw RuntimeException()
        }
    }
}