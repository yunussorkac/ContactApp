package com.app.contact.adapter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.contact.model.Contact
import kotlin.math.absoluteValue

@Composable
fun ContactAdapter(
    contact: Contact,
    onClick: (Contact) -> Unit = {}
) {

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

    val backgroundColor = remember(contact.firstName) {
        val index = (contact.firstName.hashCode().absoluteValue) % colorList.size
        colorList[index]
    }


    Row (
        modifier = Modifier.fillMaxWidth()
            .padding( top = 10.dp)
            .clickable{
                onClick(contact)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (contact.image != "null"){
            Image(
                painter = rememberAsyncImagePainter(contact.image),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
        }else{
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.firstName.substring(0, 2).uppercase(),
                    color = Color.White
                )
            }
        }


        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "${contact.firstName} ${contact.lastName}",
            modifier = Modifier.align(Alignment.CenterVertically)
                .padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium,
           )


    }

}