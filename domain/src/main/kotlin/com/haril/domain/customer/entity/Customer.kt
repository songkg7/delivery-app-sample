package com.haril.domain.customer.entity

import com.haril.domain.address.entity.CustomerAddress

class Customer(
    val id : Long? = null,
    val name: String,
    val phoneNumber: String,
    addresses: List<CustomerAddress> = listOf(),
) {
    private val _addresses: MutableList<CustomerAddress> = mutableListOf()
    val addresses: List<CustomerAddress>
        get() = _addresses.toList()

    init {
        this._addresses.addAll(addresses)
    }

    fun addAddresses(vararg addresses: CustomerAddress) {
        this._addresses.addAll(addresses)
    }

    fun removeAddress(address: CustomerAddress) {
        this._addresses.remove(address)
    }
}
