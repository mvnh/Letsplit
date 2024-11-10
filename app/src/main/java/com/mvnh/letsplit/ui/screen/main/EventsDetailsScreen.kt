package com.mvnh.letsplit.ui.screen.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.mvnh.letsplit.domain.model.EventDetails
import com.mvnh.letsplit.ui.viewmodel.state.BalanceStatus
import org.koin.core.qualifier.qualifier
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsDetailsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Назад",
                        fontWeight = FontWeight.SemiBold
                    )
                }, navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
    ) { innerPadding ->
        val bills = arrayListOf(
            EventDetails(
                title = "Продукты в магазине",
                deadline = LocalDate.of(2024, 12, 15),
                targetAmount = 1,
                collectedAmount = 750,
                balanceStatus = BalanceStatus.Positive
            ), EventDetails(
                title = "Такси до города",
                deadline = LocalDate.of(2024, 12, 15),
                targetAmount = -1,
                collectedAmount = 2500,
                balanceStatus = BalanceStatus.Negative
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
        ) {
            items(bills) { item ->

                ExpenseCard(
                    title = item.title,
                    amountOwed = item.collectedAmount.toString(),
                    (item.targetAmount).toString(),
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun ExpenseCard(
    title: String,
    amountOwed: String,
    balanceStatus: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
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
                        text = if(balanceStatus == "-1") "Вы должны " else "Вам должны ",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = amountOwed,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = if(balanceStatus == "-1") Color.Red else Color.Green
                    )
                }
            }
            DeepLinkButtons(amountOwed, "+79164681437")
        }
    }
}

@Composable
fun DeepLinkButtons(amount: String, phoneNumber: String) {
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
            Log.e("INFOG", "error")
        }
    }) {
        Text("Отправить деньги")
    }
}
