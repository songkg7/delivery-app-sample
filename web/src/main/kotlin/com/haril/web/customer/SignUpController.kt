package com.haril.web.customer

import com.haril.application.customer.command.SighUpCommand
import com.haril.application.customer.usecase.SignUpUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    private val signUpUsecase: SignUpUsecase,
) {

    @PostMapping("customer/sign-up")
    fun signUp(@RequestBody command: SighUpCommand): ResponseEntity<Unit> {
        signUpUsecase.signUp(command)
        return ResponseEntity.ok().build()
    }
}
