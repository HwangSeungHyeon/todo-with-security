package com.teamsparta.mytodoapp.domain.user.controller

import com.teamsparta.mytodoapp.domain.user.dto.request.UserLoginDto
import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import com.teamsparta.mytodoapp.domain.user.dto.response.LoginResponseDto
import com.teamsparta.mytodoapp.domain.user.dto.response.UserResponseDto
import com.teamsparta.mytodoapp.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users", description = "유저 API")
@RestController
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")
    fun singUp(
        @Valid @RequestBody userSignUpDto: UserSignUpDto
    ): ResponseEntity<UserResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.singUp(userSignUpDto))
    }

    @Operation(summary = "로그인", description = "로그인.")
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody userLoginDto: UserLoginDto
    ): ResponseEntity<LoginResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(userLoginDto))
    }
}