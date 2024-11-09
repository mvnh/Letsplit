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
fun BalanceSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BalanceCard(
            amount = { Text(text = "$567.58", style = MaterialTheme.typography.titleLarge) },
            label = { Text(text = "You Owe", style = MaterialTheme.typography.bodySmall) }
        )
        BalanceCard(
            amount = { Text(text = "$826.43", style = MaterialTheme.typography.titleLarge) },
            label = { Text(text = "Owes you", style = MaterialTheme.typography.bodySmall) }
        )
    }
}

@Composable
fun BalanceCard(amount: @Composable () -> Unit, label: @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            amount()
            label()
        }
    }
}

@Composable
fun BillItem(
    title: String,
    date: String,
    amount: String,
    balanceStatus: @Composable () -> Unit,
    balanceAmount: @Composable () -> Unit,
    balanceColor: Color
) {
    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = date, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(5.dp))
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = amount,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                thickness = 0.7.dp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .align(Alignment.End)
                    .background(balanceColor)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                balanceStatus()
                Spacer(modifier = Modifier.width(4.dp))
                balanceAmount()
            }
        }
    }
}
