package com.haril.infrastructure.customer.entity

import com.haril.domain.customer.entity.Customer
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Entity
@Table(name = "customer")
@Audited
class CustomerJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
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
            id = id,
            name = name,
            address = address,
            phoneNumber = phoneNumber,
        )
    }

}
