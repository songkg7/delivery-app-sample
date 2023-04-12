package com.haril.web.customer

import com.haril.application.customer.usecase.SignUpUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    private val signUpUsecase: SignUpUsecase,
) {

    @GetMapping("/customer")
    fun test() = "Hello"
}
