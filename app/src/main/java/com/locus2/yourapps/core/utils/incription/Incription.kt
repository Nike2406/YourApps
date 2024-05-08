package com.locus2.yourapps.core.utils.incription

import android.content.pm.Signature
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import javax.inject.Inject

class Incription @Inject constructor() {

    val md: MessageDigest = MessageDigest.getInstance("SHA-1");

    fun generateHash(signatures: Array<Signature>): String {
        for (s in signatures) {
            md.update(s.toByteArray())
            val en = Base64.encodeToString(md.digest(), Base64.DEFAULT)
            Log.e("FUCK", "generateHash: $en", )
        }
        return ""
    }
}