package com.haril.infrastructure.customer.entity

import com.haril.domain.customer.entity.Customer
import jakarta.persistence.*

@Entity
@Table(name = "customer")
class CustomerJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,
    val address: String,
    val phoneNumber: String,
) {
    companion object {
        fun from(customer: Customer): CustomerJpaEntity {
            return CustomerJpaEntity(
                name = customer.name,
                address = customer.address,
                phoneNumber = customer.phoneNumber,
            )
        }
    }

    fun toEntity(): Customer {
        return Customer(
            name = name,
            address = address,
            phoneNumber = phoneNumber,
        )
    }

}
