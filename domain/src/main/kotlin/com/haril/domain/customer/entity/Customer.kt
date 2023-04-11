package com.haril.domain.customer.entity

data class Customer(
    val id : Long? = null,
    val name: String,
    val address: String,
    val phoneNumber: String,
)
