package com.app.contact.adapter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.app.contact.model.User



@Composable
fun RowContactAdapter(
    user: User,
    onClick: (User) -> Unit = {}
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

    val backgroundColor = remember { colorList.random() }

    Column (
        modifier = Modifier.wrapContentSize()
            .padding(start = 5.dp)
            .clickable { onClick(user) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (user.image.isNotEmpty()){
            Image(
                painter = rememberAsyncImagePainter(user.image),
                contentDescription = "",
                modifier = Modifier.size(70.dp)
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
                    text = user.firstName.substring(0, 2).uppercase(),
                    color = Color.White
                )
            }
        }


        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "${user.firstName} ${user.lastName}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelSmall,
            )


    }

}