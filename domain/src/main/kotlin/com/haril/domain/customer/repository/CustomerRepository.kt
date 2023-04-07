package com.haril.domain.customer.repository

import com.haril.domain.customer.entity.Customer

interface CustomerRepository {
    fun save(customer: Customer): Customer
}
