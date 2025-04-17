package com.app.contact.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.app.contact.dao.ContactDao
import com.app.contact.db.ContactDatabase
import com.app.contact.model.Contact
import com.app.contact.viewmodel.DetailScreenViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navHostController: NavHostController, id : Int) {

    val context = LocalContext.current
    val detailScreenViewModel : DetailScreenViewModel = viewModel()
    val contactDao = ContactDatabase.getDatabase(context).contactDao()
    var contact by remember { mutableStateOf<Contact?>(null) }

    var isEditDialogVisible by remember { mutableStateOf(false) }

    var refreshState by remember { mutableStateOf(false) }


    val colorList = listOf(
        Color(0xFFEF5350),
        Color(0xFFAB47BC),
        Color(0xFF5C6BC0),
        Color(0xFF29B6F6),
        Color(0xFF66BB6A),
        Color(0xFFFFA726),
        Color(0xFFFF7043),
        Color(0xFF8D6E63),
        Color(0xFF78909C)
    )

    LaunchedEffect(Unit) {
        contact = detailScreenViewModel.getContactById(contactDao, id)
    }

    LaunchedEffect(refreshState) {
        if (refreshState) {
            contact = detailScreenViewModel.getContactById(contactDao, id)
            refreshState = false
        }
    }


    val backgroundColor = remember(contact?.firstName) {
        val index = (contact?.firstName.hashCode().absoluteValue) % colorList.size
        colorList[index]
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
                            text = "Details",
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

            Card (modifier = Modifier.fillMaxWidth().wrapContentHeight() ,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE1DBD3)
                ),
                ) {

                Spacer(modifier = Modifier.height(10.dp))

                if (contact?.image != "null"){
                    Image(
                        painter = rememberAsyncImagePainter(contact?.image),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape)
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                            .background(backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = contact?.firstName?.substring(0, 2)!!.uppercase(),
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .clickable {  },
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp),
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    VerticalDivider(
                        thickness = 1.dp,
                        color = Color.Black,
                        modifier = Modifier.width(1.dp)
                            .height(30.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))


                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .clickable{
                                isEditDialogVisible = true
                            },
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .clickable{
                                detailScreenViewModel.deleteContactById(contactDao,id)
                                navHostController.navigateUp()


                            },
                        tint = Color(0xFFE34D41)

                    )

                }


                Spacer(modifier = Modifier.height(10.dp))


                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(text = "${contact?.firstName} ${contact?.lastName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 5.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(text = contact?.number ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 5.dp))



                }


                Spacer(modifier = Modifier.height(10.dp))

            }



        }

        if (isEditDialogVisible){
            contact?.let {
                EditDialog(it,detailScreenViewModel,contactDao) {

                    isEditDialogVisible = false
                    refreshState = true

                }
            }
        }



    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(contact: Contact, detailScreenViewModel: DetailScreenViewModel,
               contactDao: ContactDao,
               onDismiss: () -> Unit){

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf(contact.firstName) }
    var lastName by remember { mutableStateOf(contact.lastName) }
    var phoneNumber by remember { mutableStateOf(contact.number) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    BackHandler {
        onDismiss()
    }


    BottomSheetScaffold(
        sheetContainerColor = Color(0xFFC9C1AD),
        scaffoldState = scaffoldState,
        sheetSwipeEnabled = false,
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


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

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val contact = Contact(
                            id = contact.id,
                            firstName = firstName,
                            lastName = lastName,
                            image = contact.image,
                            number = phoneNumber
                        )
                        detailScreenViewModel.updateContact(contactDao = contactDao,contact)
                        onDismiss()

                    }
                ) {
                    Text(text = "Update")

                }
            }
        },
        sheetPeekHeight = 0.dp,
    ) {

    }
}






