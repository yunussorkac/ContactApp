package com.app.contact.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.contact.dao.ContactDao
import com.app.contact.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}