package com.mvnh.letsplit.data.mapper

import com.mvnh.letsplit.data.network.dto.LoginUserDto
import com.mvnh.letsplit.domain.model.LoginUser

fun LoginUser.toDto() = LoginUserDto(phoneNumber, password)
fun LoginUserDto.toModel() = LoginUser(phoneNumber, password)