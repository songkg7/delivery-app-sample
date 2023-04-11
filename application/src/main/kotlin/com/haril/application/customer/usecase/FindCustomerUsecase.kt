package com.haril.application.customer.usecase

import com.haril.domain.customer.entity.Customer

interface FindCustomerUsecase {
    fun findCustomer(customerId: Long): Customer
}
