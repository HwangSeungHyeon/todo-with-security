package com.teamsparta.mytodoapp.domain.user.model

import com.teamsparta.mytodoapp.domain.common.BaseRepositoryTest
import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserEntityTest: BaseRepositoryTest(){

    @Test
    fun `toResponse - UserSignUpDto를 Entity 객체로 변환`(){
        //GIVEN
        val userSignUpDto = UserSignUpDto(
            name = "황승현",
            email = "test@gmail.com",
            password = "12345678!"
        )

        val passwordEncoder = BCryptPasswordEncoder()

        //WHEN
        val result = UserEntity.toEntity(userSignUpDto, passwordEncoder)

        //THEN
        result.userId shouldBe null
        result.name shouldBe "황승현"
        result.email shouldBe "test@gmail.com"
        result.password shouldNotBe "12345678!"
        result.role shouldBe ROLE.MEMBER
    }
}