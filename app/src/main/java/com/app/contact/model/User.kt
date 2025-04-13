package com.app.contact.model

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val image: String,
    val number: String
) : Parcelable

class UserNavType : NavType<User>(false) {
    override fun put(bundle: Bundle, key: String, value: User) {
        val json = Json.encodeToString(User.serializer(), value)
        bundle.putString(key, json)
    }

    override fun get(bundle: Bundle, key: String): User? {
        return bundle.getString(key)?.let { Json.decodeFromString(User.serializer(), it) }
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString(User.serializer(), value)
    }
}