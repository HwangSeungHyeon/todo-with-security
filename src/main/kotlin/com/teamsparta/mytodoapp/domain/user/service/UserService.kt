package com.teamsparta.mytodoapp.domain.user.service

import com.teamsparta.mytodoapp.domain.user.dto.request.UserLoginDto
import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import com.teamsparta.mytodoapp.domain.user.dto.response.LoginResponseDto
import com.teamsparta.mytodoapp.domain.user.dto.response.UserResponseDto

interface UserService {
    fun singUp(userSignUpDto: UserSignUpDto): UserResponseDto

    fun login(userLoginDto: UserLoginDto): LoginResponseDto
}