package com.app.contact.ui

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object HomeScreen

    @Serializable
    data object DetailScreen

    @Serializable
    data object AddContactScreen

}