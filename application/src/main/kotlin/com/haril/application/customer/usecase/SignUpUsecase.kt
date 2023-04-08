package com.haril.application.customer.usecase

import com.haril.application.customer.command.SighUpCommand

interface SignUpUsecase {
    fun signUp(command: SighUpCommand)
}
