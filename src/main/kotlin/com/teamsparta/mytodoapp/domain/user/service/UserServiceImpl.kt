package com.teamsparta.mytodoapp.domain.user.service

import com.teamsparta.mytodoapp.domain.exception.InvalidCredentialException
import com.teamsparta.mytodoapp.domain.exception.ModelNotFoundException
import com.teamsparta.mytodoapp.domain.user.dto.request.UserLoginDto
import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import com.teamsparta.mytodoapp.domain.user.dto.response.LoginResponseDto
import com.teamsparta.mytodoapp.domain.user.dto.response.UserResponseDto
import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import com.teamsparta.mytodoapp.domain.user.repository.UserRepository
import com.teamsparta.mytodoapp.infra.security.jwt.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
): UserService {
    override fun singUp(userSignUpDto: UserSignUpDto): UserResponseDto {
        if(userRepository.existsByEmail(userSignUpDto.email)) throw IllegalStateException("이미 가입된 이메일입니다.")

        return userRepository
            .save(UserEntity.toEntity(userSignUpDto, passwordEncoder))
            .let { UserResponseDto.toResponse(it) }
    }

    override fun login(userLoginDto: UserLoginDto): LoginResponseDto {
        //이메일 체크
        val user = userRepository.findByEmail(userLoginDto.email)
            ?: throw ModelNotFoundException("User", null)

        //가입된 사용자인지 체크, 패스워드 체크
        if (user.role.name != "MEMBER" || !passwordEncoder.matches(userLoginDto.password, user.password)){
            throw InvalidCredentialException()
        }

        return LoginResponseDto(
            accessToken = jwtProvider.generateAccessToken(user),
            refreshToken = jwtProvider.generateRefreshToken(user)
        )
    }
}