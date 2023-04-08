package com.haril.infrastructure.customer.adapter

import com.haril.domain.customer.entity.Customer
import com.haril.domain.customer.repository.CustomerRepository
import com.haril.infrastructure.customer.entity.CustomerJpaEntity
import com.haril.infrastructure.customer.repository.CustomerJpaRepository
import org.springframework.stereotype.Component

@Component
class CustomerPersistenceAdapter(
    private val customerJpaRepository: CustomerJpaRepository,
) : CustomerRepository {

    override fun save(customer: Customer): Customer {
        return customerJpaRepository.save(
            CustomerJpaEntity(
                id = customer.id,
                name = customer.name,
                address = customer.address,
                phoneNumber = customer.phoneNumber,
            )
        ).let {
            Customer(
                id = it.id,
                name = it.name,
                address = it.address,
                phoneNumber = it.phoneNumber,
            )
        }
    }
}