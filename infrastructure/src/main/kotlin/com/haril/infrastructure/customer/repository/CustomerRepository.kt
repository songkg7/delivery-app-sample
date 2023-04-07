package com.haril.infrastructure.customer.repository

import com.haril.domain.customer.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}
