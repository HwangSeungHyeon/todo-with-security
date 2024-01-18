package com.teamsparta.mytodoapp.domain.user.controller

import com.teamsparta.mytodoapp.domain.common.BaseRepositoryTest
import com.teamsparta.mytodoapp.domain.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired

class UserControllerTest @Autowired constructor(
    private val userService: UserService
): BaseRepositoryTest()