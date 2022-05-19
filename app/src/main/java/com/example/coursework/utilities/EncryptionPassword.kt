package com.example.coursework.utilities

import java.math.BigInteger
import java.security.MessageDigest

class EncryptionPassword {


    companion object {

        @JvmStatic
        fun getHashMD5(text: String): String {
            val mess = MessageDigest.getInstance("MD5")
            val pass = text.toByteArray()
            return BigInteger(1, mess.digest(pass)).toString(16).padStart(32, '0')
        }

    }

}