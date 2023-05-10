package com.haril.application.customer.command

data class AddressCommand(
    val city: String,
    val street: String,
    val zipcode: String,
)
