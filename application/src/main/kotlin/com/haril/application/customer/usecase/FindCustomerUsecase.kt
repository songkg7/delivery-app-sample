package com.haril.application.customer.usecase

import com.haril.application.customer.command.FindCustomerCommand
import com.haril.domain.customer.entity.Customer

interface FindCustomerUsecase {
    fun findCustomer(command: FindCustomerCommand): Customer
}
