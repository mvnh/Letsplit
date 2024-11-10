package com.mvnh.letsplit.data.mapper

import com.mvnh.letsplit.data.network.dto.RegisterUserDto
import com.mvnh.letsplit.domain.model.RegisterUser

fun RegisterUser.toDto() = RegisterUserDto(fullName, phoneNumber, password)
fun RegisterUserDto.toModel() = RegisterUser(fullName, phoneNumber, password)