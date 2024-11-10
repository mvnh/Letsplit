package com.mvnh.letsplit.ui.screen.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvnh.letsplit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(eventId: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = eventId,
                        fontWeight = FontWeight.SemiBold
                    )
                }, navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = R.string.back.toString()
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
    ) { innerPadding ->
        Text(
            text = eventId,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(4) { index ->
                ExpenseCard(
                    title = "Expense $index",
                    amountOwed = (index + 1) * 100,
                    transferToPhoneNumber = "+79154672943",
                    balanceStatus = if (index % 2 == 0) 1 else -1
                )
            }
        }
    }
}

@Composable
fun ExpenseCard(
    title: String,
    amountOwed: Int,
    transferToPhoneNumber: String,
    balanceStatus: Int
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column {
                    Text(
                        text = stringResource(if (balanceStatus < 0) R.string.you_owe else R.string.you_are_owed),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "$amountOwed ₽",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = if(balanceStatus < 0) Color.Red else Color.Green
                    )
                }
            }
            TransferWithTBank(amountOwed, transferToPhoneNumber)
        }
    }
}

@Composable
fun TransferWithTBank(amount: Int, phoneNumber: String) {
    val context = LocalContext.current
    val deepLinkUrl = "tbank://Main/PayByMobileNumber?numberPhone=$phoneNumber&amount=$amount"

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow
        ),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkUrl))
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("Letsplit", "Failed to start activity", e)
            }
    }) {
        Text(
            text = stringResource(R.string.transfer_money),
        )
    }
}
