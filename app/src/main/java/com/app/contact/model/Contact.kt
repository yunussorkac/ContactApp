package com.app.contact.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_list")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val image: String,
    val number: String
)

