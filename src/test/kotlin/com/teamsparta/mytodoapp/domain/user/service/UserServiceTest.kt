package com.teamsparta.mytodoapp.domain.user.service

import com.teamsparta.mytodoapp.domain.common.BaseRepositoryTest
import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import com.teamsparta.mytodoapp.domain.user.model.ROLE
import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import com.teamsparta.mytodoapp.domain.user.repository.UserRepository
import com.teamsparta.mytodoapp.infra.security.jwt.JwtProvider
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository
): BaseRepositoryTest(){

    val passwordEncoder = BCryptPasswordEncoder()

    val jwtProvider =  JwtProvider(
        secret = "",
        issuer = "team.sparta.com",
        accessExpiration = 1L,
        refreshExpiration = 168L
    )

    private val userServiceImpl = UserServiceImpl(userRepository, passwordEncoder, jwtProvider)

    @Test
    fun `singUp - DB에 사용자 정보가 없을 경우 회원가입처리`(){
        //GIVEN
        userRepository.deleteAll()

        val userSignUpDto = UserSignUpDto(
            name = "황승현",
            email = "test@gmail.com",
            password = "12345678!"
        )

        //WHEN
        val result = userServiceImpl.singUp(userSignUpDto)

        //THEN
        result.userId shouldNotBe null
        result.name shouldBe "황승현"
        result.email shouldBe "test@gmail.com"
        result.role shouldBe ROLE.MEMBER

        userRepository.findAll().toList().let{
            it.size shouldBe 1 //db에 하나만 있을 경우 사용
            it[0].name shouldBe "황승현"
            it[0].email shouldBe "test@gmail.com"
            it[0].role shouldBe ROLE.MEMBER
        }
    }

    @Test
    fun `singUp - DB에 사용자 정보가 있을 경우 IllegalStateException 실행`() {
        //GIVEN
        val userSignUpDto = UserSignUpDto(
            name = "황승현",
            email = "test@gmail.com",
            password = "12345678!"
        )

        val DB에넣기전Entity = UserEntity.toEntity(userSignUpDto, passwordEncoder)

        val DB에있는사용자정보 = userRepository.save(DB에넣기전Entity)

        //WHEN
        val exception = shouldThrowAny {
            userServiceImpl.singUp(
                UserSignUpDto(
                    name = "황승현",
                    email = "test@gmail.com",
                    password = "12345678!"
                )
            )
        }

        //THEN
        exception shouldBe IllegalStateException("이미 가입된 이메일입니다.")
    }


}