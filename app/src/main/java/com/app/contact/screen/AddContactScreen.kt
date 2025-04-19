package com.app.contact.screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.app.contact.model.Contact
import com.app.contact.viewmodel.AddContactScreenViewModel
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(navHostController: NavHostController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val addContactScreenViewModel = hiltViewModel<AddContactScreenViewModel>()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            val resolver = context.contentResolver
            resolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            imagePickerLauncher.launch(arrayOf("image/*"))
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add Contact",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = {
                    Text(text = "First Name")
                },
                shape = CircleShape
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = {
                    Text(text = "Last Name")
                },
                shape = CircleShape
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                label = {
                    Text(text = "Phone Number")
                },
                shape = CircleShape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Select Image",
                    modifier = Modifier.size(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            when {
                                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                                    if (ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.READ_MEDIA_IMAGES
                                        ) == PackageManager.PERMISSION_GRANTED
                                    ) {
                                        imagePickerLauncher.launch(arrayOf("image/*"))
                                    } else {
                                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                                    }
                                }

                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                ) == PackageManager.PERMISSION_GRANTED -> {
                                    imagePickerLauncher.launch(arrayOf("image/*"))
                                }

                                else -> {
                                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                }
                            }
                        },
                    tint = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    addContactScreenViewModel.insertContact(
                        Contact(
                            firstName = firstName,
                            lastName = lastName,
                            image = selectedImageUri.toString(),
                            number = phoneNumber)
                    ) {
                        if (it) {
                            navHostController.navigateUp()
                        }

                    }



                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF006874),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 100.dp)
            ) {
                Text(text = "Save", style = MaterialTheme.typography.titleLarge)
            }


        }
    }
}