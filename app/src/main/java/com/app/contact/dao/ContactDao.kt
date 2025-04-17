package com.app.contact.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.contact.model.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contact_list")
    suspend fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contact_list WHERE id = :contactId")
    suspend fun getContactById(contactId: Int): Contact?

    @Query("DELETE FROM contact_list WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Int)

    @Update
    suspend fun updateContact(contact: Contact)
}