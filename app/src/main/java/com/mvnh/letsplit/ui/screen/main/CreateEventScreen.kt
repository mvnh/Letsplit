package com.mvnh.letsplit.ui.screen.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mvnh.letsplit.domain.model.EventRequest
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen() {
    var eventName by rememberSaveable { mutableStateOf("") }
    var eventDescription by rememberSaveable { mutableStateOf("") }
    var startDate by rememberSaveable { mutableStateOf("") }
    var endDate by rememberSaveable { mutableStateOf("") }
    val ownerUuid = UUID.randomUUID().toString()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Создать событие") },
                navigationIcon = {
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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Создать событие") },
                icon = { Icon(imageVector = Icons.Default.Check, contentDescription = null) },
                onClick = {
                    coroutineScope.launch {
                        if (eventName.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                            val newEvent = EventRequest(
                                name = eventName,
                                description = eventDescription,
                                createdAt = startDate,
                                until = endDate,
                                ownerUuid = ownerUuid
                            )
                        } else {

                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            InputField(
                label = "Название события",
                value = eventName,
                onValueChange = { eventName = it }
            )

            InputField(
                label = "Описание события",
                value = eventDescription,
                onValueChange = { eventDescription = it }
            )

            InputField(
                label = "Дата начала",
                value = startDate,
                onValueChange = { startDate = it }
            )

            InputField(
                label = "Дата окончания",
                value = endDate,
                onValueChange = { endDate = it }
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            ),
        colors = colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = Color.White,
            disabledTextColor = Color.White
        )
    )
}

