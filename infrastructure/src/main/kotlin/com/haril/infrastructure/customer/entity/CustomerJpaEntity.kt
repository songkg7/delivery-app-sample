package com.haril.infrastructure.customer.entity

import com.haril.domain.customer.entity.Customer
import com.haril.infrastructure.address.entity.CustomerAddressEntity
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
    val phoneNumber: String,
    addresses: List<CustomerAddressEntity> = emptyList(),
) {
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val addresses: MutableList<CustomerAddressEntity> = mutableListOf()

    init {
        this.addresses.addAll(addresses)
    }

    fun addAddresses(addresses: List<CustomerAddressEntity>) {
        this.addresses.addAll(addresses)
    }

    fun removeAddress(address: CustomerAddressEntity) {
        this.addresses.remove(address)
    }

    companion object {
        fun from(customer: Customer): CustomerJpaEntity {
            val customerJpaEntity = CustomerJpaEntity(
                name = customer.name,
                phoneNumber = customer.phoneNumber,
            )
            customerJpaEntity.addAddresses(customer.addresses.map { CustomerAddressEntity.from(it, customerJpaEntity) })
            return customerJpaEntity
        }
    }

    fun toEntity(): Customer {
        return Customer(
            id = id,
            name = name,
            phoneNumber = phoneNumber,
            addresses = addresses.map { it.toEntity() }
        )
    }

}
