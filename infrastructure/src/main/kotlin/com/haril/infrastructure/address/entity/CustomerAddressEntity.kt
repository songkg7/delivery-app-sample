package com.haril.infrastructure.address.entity

import com.haril.domain.address.entity.CustomerAddress
import com.haril.infrastructure.customer.entity.CustomerJpaEntity
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Entity
@Audited
class CustomerAddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val customer: CustomerJpaEntity,
    val city: String,
    val street: String,
    val zipcode: String,
) {

    companion object {
        fun from(customerAddress: CustomerAddress, customer: CustomerJpaEntity): CustomerAddressEntity {
            return CustomerAddressEntity(
                id = customerAddress.id,
                customer = customer,
                city = customerAddress.city,
                street = customerAddress.street,
                zipcode = customerAddress.zipcode,
            )
        }
    }

    fun toEntity(): CustomerAddress {
        return CustomerAddress(
            id = id,
            city = city,
            street = street,
            zipcode = zipcode,
        )
    }
}
