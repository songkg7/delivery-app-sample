package com.haril.application.customer.service

import com.haril.application.customer.command.SighUpCommand
import com.haril.application.customer.usecase.SignUpUsecase
import com.haril.domain.customer.entity.Customer
import com.haril.domain.customer.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignUpService(
    private val customerRepository: CustomerRepository,
) : SignUpUsecase {

    override fun signUp(command: SighUpCommand) {
        Customer(
            name = command.name,
            address = command.address,
            phoneNumber = command.phoneNumber,
        ).let(customerRepository::save)
    }
}
