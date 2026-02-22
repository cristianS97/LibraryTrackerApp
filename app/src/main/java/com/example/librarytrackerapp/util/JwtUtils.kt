package com.example.librarytrackerapp.util

import android.util.Base64
import org.json.JSONObject

object JwtUtils {
    fun getRoleFromToken(token: String?): String? {
        if (token == null) return null
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null

            // Decodificar Base64 del Payload
            val payload64 = parts[1]
            val decodedBytes = Base64.decode(payload64, Base64.URL_SAFE)
            val payloadString = String(decodedBytes, Charsets.UTF_8)

            // Extraer el rol del JSON
            val jsonObject = JSONObject(payloadString)
            jsonObject.optString("role", null)
        } catch (e: Exception) {
            null
        }
    }
}