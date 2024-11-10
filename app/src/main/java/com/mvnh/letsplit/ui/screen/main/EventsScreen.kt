package com.mvnh.letsplit.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvnh.letsplit.R

data class EventInfo(
    val title: String,
    val date: String,
    val amount: String,
    val balanceStatus: String,
    val balanceAmount: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Letsplit",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .size(40.dp),
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BalanceSection()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "All events",
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            val events = listOf(
                EventInfo("Birthday House", "Mar 24, 2023", "$4508.32", "You are owed: ", "$3005.54"),
                EventInfo("Shopping", "Mar 24, 2023", "$505.00", "You owe: ", "$89.00"),
                EventInfo("Grocery Shopping", "Mar 20, 2023", "$200.00", "You are owed: ", "$50.00"),
                EventInfo("Concert", "Mar 18, 2023", "$150.00", "You owe: ", "$30.00")
            )

            events.forEach { event ->
                BillItem(
                    title = event.title,
                    date = event.date,
                    amount = event.amount,
                    balanceStatus = {
                        Text(text = event.balanceStatus, style = MaterialTheme.typography.bodySmall)
                    },
                    balanceAmount = {
                        Text(text = event.balanceAmount, style = MaterialTheme.typography.bodyLarge)
                    },
                    balanceColor = if (event.balanceStatus == "You owe: ") Color(0xFF08FF44).copy(alpha = 0.3f) else Color(0xFFFF0000).copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
fun BalanceCard(
    amount: Int,
    type: BalanceStatus,
    label: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (type) {
                BalanceStatus.Positive -> Color.Green
                BalanceStatus.Negative -> Color.Red
                else -> Color.Gray
            }.copy(alpha = 0.25f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "$amount ₽",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
            label()
        }
    }
}

@Composable
fun EventCardsSection(
    @StringRes titleRes: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .paddingFromBaseline(bottom = 8.dp)
                .padding(horizontal = 8.dp)
        )
        content()
    }
}

@Composable
fun EventCard(eventDetails: EventDetails) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = eventDetails.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text =  "${stringResource(R.string.until)} ${eventDetails.deadline.dayOfMonth} ${eventDetails.deadline.month}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${eventDetails.targetAmount} / ",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
                Text(
                    text = eventDetails.targetAmount.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row {
                Spacer(modifier = Modifier.weight(0.5f))

                Row(
                    modifier = Modifier
                        .background(
                        color = when (eventDetails.balanceStatus) {
                            BalanceStatus.Positive -> Color.Green
                            BalanceStatus.Negative -> Color.Red
                        }.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = when (eventDetails.balanceStatus) {
                            BalanceStatus.Positive -> stringResource(R.string.you_are_owed)
                            BalanceStatus.Negative -> stringResource(R.string.you_owe)
                        } + " ${eventDetails.collectedAmount} ₽",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}