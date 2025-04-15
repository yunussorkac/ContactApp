package com.app.contact.screen

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.app.contact.adapter.ContactAdapter
import com.app.contact.adapter.RowContactAdapter
import com.app.contact.db.ContactDatabase
import com.app.contact.model.Contact
import com.app.contact.ui.Screens
import com.app.contact.viewmodel.HomeScreenViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreen(navHostController: NavHostController){

    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    val homeScreenViewModel : HomeScreenViewModel = viewModel()
    val contactDao = ContactDatabase.getDatabase(context).contactDao()
    val contactList by homeScreenViewModel.contactList.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.getAllContacts(contactDao)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screens.AddContactScreen)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Contacts",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(text = "Search") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = CircleShape,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Recent Added",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                items(contactList.takeLast(10).reversed()) { contact ->
                    RowContactAdapter(contact)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "My Contacts (${contactList.size})",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                items(contactList) { contact ->
                    println(contact.image)
                    ContactAdapter(contact){
                    }
                }
            }
        }
    }
}