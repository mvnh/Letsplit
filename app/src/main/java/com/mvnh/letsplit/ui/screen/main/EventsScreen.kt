package com.mvnh.letsplit.ui.screen.main

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvnh.letsplit.R
import com.mvnh.letsplit.domain.model.EventDetails
import com.mvnh.letsplit.ui.viewmodel.state.BalanceStatus
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Letsplit",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                BalanceCardsSection(
                    titleRes = R.string.balance,
                    content = {
                        BalanceCard(
                            amount = 1000,
                            type = BalanceStatus.Negative,
                            label = {
                                Text(
                                    text = stringResource(R.string.you_owe),
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        )
                        BalanceCard(
                            amount = 1000,
                            type = BalanceStatus.Positive,
                            label = {
                                Text(
                                    text = stringResource(R.string.you_are_owed),
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        )
                    }
                )
            }
            item {
                EventCardsSection(titleRes = R.string.events) {
                    EventCard(
                        eventDetails = EventDetails(
                            title = "Event 1",
                            deadline = LocalDate.now().plusDays(1),
                            targetAmount = 1000,
                            collectedAmount = 523,
                            balanceStatus = BalanceStatus.Positive
                        )
                    )
                    EventCard(
                        eventDetails = EventDetails(
                            title = "Event 2",
                            deadline = LocalDate.now().plusDays(2),
                            targetAmount = 1000,
                            collectedAmount = -523,
                            balanceStatus = BalanceStatus.Negative
                        )
                    )
                    EventCard(
                        eventDetails = EventDetails(
                            title = "Event 4",
                            deadline = LocalDate.now().plusDays(4),
                            targetAmount = 1000,
                            collectedAmount = 1000,
                            balanceStatus = BalanceStatus.Positive
                        )
                    )
                    EventCard(
                        eventDetails = EventDetails(
                            title = "Event 5",
                            deadline = LocalDate.now().plusDays(5),
                            targetAmount = 1000,
                            collectedAmount = -1000,
                            balanceStatus = BalanceStatus.Negative
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun BalanceCardsSection(
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