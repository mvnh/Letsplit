package com.mvnh.letsplit.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.mvnh.letsplit.R
import com.mvnh.letsplit.domain.model.InvitationDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventInvitationsScreen() {
    val invitations = arrayListOf(
        InvitationDetails(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png",
            creatorName = "Anna Ivanova",
            eventName = "Продукты в магазине",
            until = "24 Mar 2024",
            status = "pending"
        ),
        InvitationDetails(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png",
            creatorName = "Mikhail Sidorov",
            eventName = "Продукты в магазине",
            until = "10 Feb 2024",
            status = "pending"
        ),
        InvitationDetails(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png",
            creatorName = "Ekaterina Petrova",
            eventName = "Продукты в магазине",
            until = "15 Apr 2024",
            status = "accepted"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.notifications),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Заявок нет")
        }
//        LazyColumn(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ) {
//            items(invitations) { invitation ->
//                InvitationItem(
//                    invitation.imageUrl,
//                    invitation.creatorName,
//                    invitation.eventName,
//                    invitation.until,
//                    invitation.status
//                )
//            }
//        }
    }
}

@Composable
fun InvitationItem(
    imageUrl: String,
    creatorName: String,
    eventName: String,
    until: String,
    status: String
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(25.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = creatorName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = eventName,
                    fontSize = 14.sp
                )
                Text(
                    text = "Until $until",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Reject")
                }

                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C79FF),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Accept")
                }
            }
        }
    }
}

