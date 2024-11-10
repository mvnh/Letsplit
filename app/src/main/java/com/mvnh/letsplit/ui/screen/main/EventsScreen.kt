package com.mvnh.letsplit.ui.screen.main

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.navigation.NavController
import com.mvnh.letsplit.R
import com.mvnh.letsplit.domain.model.EventDetails
import com.mvnh.letsplit.ui.navigation.Screen
import com.mvnh.letsplit.ui.viewmodel.EventsViewModel
import com.mvnh.letsplit.ui.viewmodel.state.BalanceStatus
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    navController: NavController,
    viewModel: EventsViewModel = koinViewModel()
) {
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.CreateEvent.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_event)
                )
            }
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
                    viewModel.getEvents().forEach { eventDetails ->
                        EventCard(navController, eventDetails)
                    }
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
fun EventCard(navController: NavController, eventDetails: EventDetails) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("${Screen.EventDetails.route}/${eventDetails.title}") },
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