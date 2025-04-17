package com.app.contact.ui

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object HomeScreen

    @Serializable
    data class DetailScreen(val id : Int)

    @Serializable
    data object AddContactScreen


}